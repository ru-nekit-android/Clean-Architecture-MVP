package ru.nekit.android.clean_architecture.di.modules;

import android.support.annotation.NonNull;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.data.api.GithubApi;
import ru.nekit.android.clean_architecture.data.repository.GithubRepository;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.LongOperationThread;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.MainThread;
import rx.Scheduler;

@Module
@Singleton
public class TestDataModule {

    @Provides
    @NonNull
    public IGithubRepository provideGithubRepository(GithubApi githubApi,
                                                     @LongOperationThread Scheduler longOperationThread,
                                                     @MainThread Scheduler mainThread) {
        return Mockito.mock(GithubRepository.class);
    }
}
