package ru.nekit.android.clean_architecture.presentation.internal.di.modules;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.BuildConfig;
import ru.nekit.android.clean_architecture.data.api.qualifier.Endpoint;
import ru.nekit.android.clean_architecture.data.repository.LongOperationThread;
import ru.nekit.android.clean_architecture.data.repository.MainThread;
import ru.nekit.android.clean_architecture.data.repository.GithubRepository;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
public class DataModule {

    @Provides
    @Singleton
    @LongOperationThread
    @NonNull
    Scheduler provideLongOperationScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @MainThread
    @NonNull
    Scheduler provideUIThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @NonNull
    IGithubRepository provideGithubRepository(GithubRepository repository) {
        return repository;
    }

    @Provides
    @Singleton
    @Endpoint
    @NonNull
    String provideEndpoint() {
        return BuildConfig.API_URL;
    }

}
