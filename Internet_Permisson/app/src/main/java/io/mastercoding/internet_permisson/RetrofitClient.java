package io.mastercoding.internet_permisson;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    public static Retrofit get() {
        if (retrofit == null) {

            // 1) Logging interceptor (only for debug)
            HttpLoggingInterceptor log = new HttpLoggingInterceptor();
            log.setLevel(HttpLoggingInterceptor.Level.BASIC); // or BODY for full logs

            // 2) Optional: Add headers globally (e.g., API keys, Authorization)
            Interceptor headers = chain -> {
                Request original = chain.request();
                Request req = original.newBuilder()
                        // .addHeader("Authorization", "Bearer yourToken")
                        // .addHeader("Accept", "application/json")
                        .build();
                return chain.proceed(req);
            };

            // setup
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(log)
                    .addInterceptor(headers)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/") // MUST end with /
                    .addConverterFactory(GsonConverterFactory.create()) // JSON -> Java
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static ApiService api() {
        return get().create(ApiService.class);
    }
}