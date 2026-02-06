package io.mastercoding.internet_permisson;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NetworkMonitor.Listener {

    private TextView txtResult;
    private ProgressBar progress;

    private NetworkMonitor networkMonitor;
    private Dialogs dialogs;

    private OnBackPressedCallback backCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);
        progress = findViewById(R.id.progress);

        dialogs = new Dialogs();
        networkMonitor = new NetworkMonitor(this);
        networkMonitor.addListener(this);
        networkMonitor.start();

        // First attempt
        tryFetchPosts();

        // Standard back handling (if you still have WebView elsewhere, keep that there)
        backCallback = new OnBackPressedCallback(true) {
            @Override public void handleOnBackPressed() { finish(); }
        };
        getOnBackPressedDispatcher().addCallback(this, backCallback);
    }

    private void tryFetchPosts() {
        if (!networkMonitor.hasInternet()) {
            dialogs.showNoInternetDialog(this, this::tryFetchPosts);
            return;
        }

        dialogs.dismissNoInternetDialog();
        showLoading(true);

        RetrofitClient.api().getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                showLoading(false);
                if (response.isSuccessful() && response.body() != null) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < Math.min(5, response.body().size()); i++) {
                        sb.append("• ").append(response.body().get(i).title).append("\n");
                    }
                    txtResult.setText(sb.toString());
                    Toast.makeText(MainActivity.this, "API success", Toast.LENGTH_SHORT).show();
                } else {
                    txtResult.setText("API error: " + response.code());
                    Toast.makeText(MainActivity.this, "API error " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                showLoading(false);
                if (t instanceof IOException) {
                    // Likely network error; show dialog again
                    dialogs.showNoInternetDialog(MainActivity.this, MainActivity.this::tryFetchPosts);
                }
                txtResult.setText("Failure: " + t.getMessage());
            }
        });
    }

    private void showLoading(boolean show) {
        progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    // === NetworkMonitor.Listener callbacks ===
    @Override
    public void onAvailable() {
        // Auto-dismiss dialog & fetch when net returns
        runOnUiThread(() -> {
            dialogs.dismissNoInternetDialog();
            tryFetchPosts();
        });
    }

    @Override
    public void onLost() {
        runOnUiThread(() ->
                dialogs.showNoInternetDialog(MainActivity.this, MainActivity.this::tryFetchPosts)
        );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        networkMonitor.removeListener(this);
        networkMonitor.stop();
        if (backCallback != null) backCallback.remove();
        dialogs.dismissNoInternetDialog();
    }
}