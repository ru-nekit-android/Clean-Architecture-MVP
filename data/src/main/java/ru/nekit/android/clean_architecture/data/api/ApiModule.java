package ru.nekit.android.clean_architecture.data.api;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.nekit.android.clean_architecture.data.api.qualifier.Endpoint;
import ru.nekit.android.data.BuildConfig;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
@Module
public class ApiModule {

    @Singleton
    @Provides
    @NonNull
    public GitHubApi provideApiService(@NonNull @Endpoint String endpoint, @NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .validateEagerly(BuildConfig.DEBUG) // Fail early: check Retrofit configuration at creation time in Debug build.
                .build()
                .create(GitHubApi.class);
    }
}
