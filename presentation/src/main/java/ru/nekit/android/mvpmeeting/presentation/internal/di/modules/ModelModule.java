package ru.nekit.android.mvpmeeting.presentation.internal.di.modules;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.mvpmeeting.BuildConfig;
import ru.nekit.android.mvpmeeting.domain.repository.IGithubRepository;
import ru.nekit.android.mvpmeeting.model.Const;
import ru.nekit.android.mvpmeeting.model.repository.GithubRepository;
import ru.nekit.android.mvpmeeting.model.internal.di.Endpoint;
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
    @Named(Const.PRE_THREAD)
    Scheduler providePreScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named(Const.POST_THREAD)
    Scheduler providePostScheduler() {
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