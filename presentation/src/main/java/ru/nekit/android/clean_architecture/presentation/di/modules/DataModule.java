package ru.nekit.android.clean_architecture.presentation.di.modules;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.data.api.di.GithubModule;
import ru.nekit.android.clean_architecture.data.repository.GithubRepository;
import ru.nekit.android.clean_architecture.data.repository.di.qualifier.LongOperationThread;
import ru.nekit.android.clean_architecture.data.repository.di.qualifier.MainThread;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module(includes = {GithubModule.class})
@Singleton
public class DataModule {

    @Provides
    @LongOperationThread
    @NonNull
    Scheduler provideLongOperationScheduler() {
        return Schedulers.io();
    }

    @Provides
    @MainThread
    @NonNull
    Scheduler provideUIThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @NonNull
    IGithubRepository provideGithubRepository(GithubRepository repository) {
        return repository;
    }

}
