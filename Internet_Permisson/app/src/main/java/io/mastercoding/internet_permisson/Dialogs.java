package io.mastercoding.internet_permisson;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.provider.Settings;

public class Dialogs {

    private AlertDialog internetDialog;

    public void showNoInternetDialog(Activity activity, Runnable onRetry) {
        if (internetDialog != null && internetDialog.isShowing()) return;

        internetDialog = new AlertDialog.Builder(activity)
                .setTitle("Internet connection lost")
                .setMessage("Please check your connection. We will auto-retry when it’s back.")
                .setCancelable(false)
                .setPositiveButton("Retry now", (d, which) -> {
                    if (onRetry != null) onRetry.run();
                })
                .setNegativeButton("Open Settings", (d, which) -> {
                    activity.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                })
                .create();
        internetDialog.show();
    }

    public void dismissNoInternetDialog() {
        if (internetDialog != null && internetDialog.isShowing()) {
            internetDialog.dismiss();
        }
    }
}
