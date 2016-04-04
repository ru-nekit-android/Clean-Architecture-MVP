package ru.nekit.android.clean_architecture.presentation.di.modules;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.domain.interactors.RequestRepositoryListUseCase;
import ru.nekit.android.clean_architecture.presentation.core.navigation.NavigationCommand;
import ru.nekit.android.clean_architecture.presentation.model.GithubModel;
import ru.nekit.android.clean_architecture.presentation.model.IGithubModel;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.NavigateToRepositoryInfo;
import ru.nekit.android.clean_architecture.presentation.presenter.RepositoryListPresenter;
import ru.nekit.android.clean_architecture.presentation.presenter.mapper.RepositoryToModelMapper;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
@Singleton
public class PresentationModule {

    @Provides
    @NonNull
    public RepositoryListPresenter provideRepositoryListPresenter(@NonNull IGithubModel model,
                                                                  @NonNull RequestRepositoryListUseCase interactor,
                                                                  @NonNull RepositoryToModelMapper mapper) {
        return new RepositoryListPresenter(model, interactor, mapper);
    }

    @Provides
    @NonNull
    public IGithubModel provideIGithubModel() {
        return new GithubModel();
    }

    @Provides
    @NavigateToRepositoryInfo
    @NonNull
    public NavigationCommand provideNavigationCommand() {
        return () -> {
            //no-op
        };
    }
}
