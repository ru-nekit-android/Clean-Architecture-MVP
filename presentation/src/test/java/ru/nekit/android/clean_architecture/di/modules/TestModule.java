package ru.nekit.android.clean_architecture.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.data.di.api.GithubModule;

/**
 * Created by ru.nekit.android on 20.04.16.
 */
@Module
@Singleton
public class TestModule {

    @Provides
    public GithubModule.Api provideGithubModule_Api() {
        return user -> null;
    }

}
