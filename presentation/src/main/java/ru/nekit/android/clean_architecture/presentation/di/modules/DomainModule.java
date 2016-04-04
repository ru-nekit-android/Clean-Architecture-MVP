package ru.nekit.android.clean_architecture.presentation.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.domain.interactors.RequestRepositoryListUseCase;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
@Singleton
public class DomainModule {

    @Provides
    public RequestRepositoryListUseCase provideObtainRepositoriesInteractor(IGithubRepository repository) {
        return new RequestRepositoryListUseCase(repository);
    }

}
