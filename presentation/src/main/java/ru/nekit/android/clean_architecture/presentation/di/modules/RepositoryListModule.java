package ru.nekit.android.clean_architecture.presentation.di.modules;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.domain.interactors.RequestRepositoryListUseCase;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import ru.nekit.android.clean_architecture.presentation.di.scope.RepositoryListScope;
import ru.nekit.android.clean_architecture.presentation.model.GithubModel;
import ru.nekit.android.clean_architecture.presentation.model.IGithubModel;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
@RepositoryListScope
public class RepositoryListModule {

    @Provides
    @NonNull
    public RequestRepositoryListUseCase provideRequestRepositoryListUseCase(IGithubRepository repository) {
        return new RequestRepositoryListUseCase(repository);
    }

    @Provides
    @NonNull
    public IGithubModel provideIGithubModel() {
        return new GithubModel();
    }

}
