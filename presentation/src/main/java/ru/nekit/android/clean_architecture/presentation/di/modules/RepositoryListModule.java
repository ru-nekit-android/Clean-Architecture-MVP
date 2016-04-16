package ru.nekit.android.clean_architecture.presentation.di.modules;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.domain.interactors.RequestRepositoryListUseCase;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import ru.nekit.android.clean_architecture.presentation.di.scope.RepositoryListScope;
import ru.nekit.android.clean_architecture.presentation.model.RepositoryListViewModel;
import ru.nekit.android.clean_architecture.presentation.model.IRepositoryListViewModel;

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
    public IRepositoryListViewModel provideIGithubModel() {
        return new RepositoryListViewModel();
    }

}
