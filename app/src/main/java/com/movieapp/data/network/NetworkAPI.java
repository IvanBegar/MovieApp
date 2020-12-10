package com.movieapp.data.network;

import com.movieapp.BuildConfig;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkAPI {

    private static String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = BuildConfig.API_KEY;

    private static Retrofit retrofit;

    private static OkHttpClient getHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            HttpUrl originalUrl = original.url();
            HttpUrl url = originalUrl.newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build();
            Request.Builder requestBuilder = original.newBuilder().url(url);
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
        return httpClient.build();
    }

    public static Retrofit getRetroClient() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .client(getHttpClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
