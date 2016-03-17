package ru.nekit.android.mvpmeeting.presentation.internal.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.mvpmeeting.domain.interactors.ObtainRepositoriesInteractor;
import ru.nekit.android.mvpmeeting.domain.repository.IGithubRepository;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
public class DomainModule {

    @Singleton
    @Provides
    public ObtainRepositoriesInteractor provideObtainRepositoriesInteractor(IGithubRepository repository) {
        return new ObtainRepositoriesInteractor(repository);
    }

}
