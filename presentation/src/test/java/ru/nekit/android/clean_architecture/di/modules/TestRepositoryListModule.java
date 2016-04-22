package ru.nekit.android.clean_architecture.di.modules;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.domain.interactors.RequestRepositoryListUseCase;
import ru.nekit.android.clean_architecture.presentation.di.scope.RepositoryListScope;
import ru.nekit.android.clean_architecture.presentation.model.IRepositoryListViewModel;
import ru.nekit.android.clean_architecture.presentation.model.RepositoryListViewModel;

import static org.mockito.Mockito.mock;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
@RepositoryListScope
public class TestRepositoryListModule {

    @Provides
    @NonNull
    public RequestRepositoryListUseCase provideRequestRepositoryListUseCase() {
        return mock(RequestRepositoryListUseCase.class);
    }

    @Provides
    @NonNull
    public IRepositoryListViewModel provideIGithubModel() {
        return mock(RepositoryListViewModel.class);
    }

}
