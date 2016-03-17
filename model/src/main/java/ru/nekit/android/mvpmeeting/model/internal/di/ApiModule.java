package ru.nekit.android.mvpmeeting.model.internal.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import ru.nekit.android.mvpmeeting.model.net.GitHubApiService;

/**
 * Created by MacOS on 02.03.16.
 */
@Module
public class ApiModule {

    @Singleton
    @Provides
    public GitHubApiService provideApiService(@Endpoint String endpoint) {

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        return builder.build().create(GitHubApiService.class);

    }
}
