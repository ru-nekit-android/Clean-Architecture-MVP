package ru.nekit.android.clean_architecture.presentation.internal.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.domain.interactors.ObtainRepositoriesInteractor;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;

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
