package ru.nekit.android.mvpmeeting.model.internal.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.mvpmeeting.model.internal.di.qualifier.Endpoint;
import ru.nekit.android.mvpmeeting.model.net.GitHubApiService;

/**
 * Created by MacOS on 02.03.16.
 */
@Module
public class ApiModule {

    @Singleton
    @Provides
    public GitHubApiService provideApiService(@Endpoint String endpoint) {
        return GitHubApiService.Factory.create(endpoint);
    }
}
