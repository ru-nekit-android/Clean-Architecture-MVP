package ru.nekit.android.clean_architecture.di.modules;

import android.support.annotation.NonNull;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.domain.interactors.RequestRepositoryListUseCase;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.UserName;
import ru.nekit.android.clean_architecture.presentation.di.scope.RepositoryListScope;
import ru.nekit.android.clean_architecture.presentation.model.IRepositoryListViewModel;
import ru.nekit.android.clean_architecture.presentation.model.RepositoryListViewModel;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
@RepositoryListScope
public class TestRepositoryListModule {

    @Provides
    @NonNull
    public RequestRepositoryListUseCase provideRequestRepositoryListUseCase(IGithubRepository repository) {
        return Mockito.mock(RequestRepositoryListUseCase.class);
    }

    @Provides
    @NonNull
    public IRepositoryListViewModel provideIGithubModel(@UserName String defaultUserName) {
        return Mockito.mock(RepositoryListViewModel.class);
    }

}
