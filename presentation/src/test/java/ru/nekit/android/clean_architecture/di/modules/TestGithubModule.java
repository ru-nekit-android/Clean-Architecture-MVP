package ru.nekit.android.clean_architecture.di.modules;

import android.support.annotation.NonNull;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.data.api.GithubApi;

/**
 * Created by ru.nekit.android on 22.04.16.
 */
@Module
@Singleton
public class TestGithubModule {

    @Provides
    @NonNull
    public GithubApi provideApi() {
        return Mockito.mock(GithubApi.class);
    }

}
