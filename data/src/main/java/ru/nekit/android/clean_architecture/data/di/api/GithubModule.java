package ru.nekit.android.clean_architecture.data.di.api;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.nekit.android.clean_architecture.data.api.GithubApi;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
@Module
@Singleton
public class GithubModule {

    @Provides
    @NonNull
    public GithubApi provideApi(@NonNull Retrofit retrofit) {
        return retrofit.create(GithubApi.class);
    }

}
