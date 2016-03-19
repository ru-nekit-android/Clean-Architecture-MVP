package ru.nekit.android.mvpmeeting.presentation.internal.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.mvpmeeting.BuildConfig;
import ru.nekit.android.mvpmeeting.domain.repository.IGithubRepository;
import ru.nekit.android.mvpmeeting.model.internal.di.qualifier.Endpoint;
import ru.nekit.android.mvpmeeting.model.internal.di.qualifier.IOThread;
import ru.nekit.android.mvpmeeting.model.internal.di.qualifier.UIThread;
import ru.nekit.android.mvpmeeting.model.repository.GithubRepository;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
public class ModelModule {

    @Provides
    @Singleton
    @IOThread
    Scheduler provideLongOperationScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @UIThread
    Scheduler provideUIThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    IGithubRepository provideGithubRepository(GithubRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    @Endpoint
    String provideEndpoint() {
        return BuildConfig.API_URL;
    }

}
