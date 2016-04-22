package ru.nekit.android.clean_architecture.presentation.di.modules;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.data.di.api.GithubModule;
import ru.nekit.android.clean_architecture.data.repository.GithubRepository;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.LongOperationThread;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.MainThread;
import rx.Scheduler;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
@Singleton
public class DataModule {

    @Provides
    @NonNull
    public IGithubRepository provideGithubRepository(GithubModule.Api githubApi,
                                                     @LongOperationThread Scheduler longOperationThread,
                                                     @MainThread Scheduler mainThread) {
        return new GithubRepository(githubApi, longOperationThread, mainThread);
    }

}
