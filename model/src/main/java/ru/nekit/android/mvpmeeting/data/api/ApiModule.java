package ru.nekit.android.mvpmeeting.data.api;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by MacOS on 02.03.16.
 */
public class ApiModule {

    public static ApiInterface getApiInterface() {

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        return builder.build().create(ApiInterface.class);

    }
}
