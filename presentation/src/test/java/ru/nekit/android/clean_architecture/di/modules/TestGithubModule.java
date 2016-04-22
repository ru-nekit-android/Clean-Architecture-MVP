package ru.nekit.android.clean_architecture.di.modules;

import android.support.annotation.NonNull;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import ru.nekit.android.clean_architecture.data.di.api.GithubModule;

/**
 * Created by ru.nekit.android on 22.04.16.
 */
@Module
@Singleton
public class TestGithubModule {

    @Provides
    @NonNull
    public GithubModule.Api provideApi(@NonNull Retrofit retrofit) {
        return Mockito.mock(GithubModule.Api.class);
    }
}
