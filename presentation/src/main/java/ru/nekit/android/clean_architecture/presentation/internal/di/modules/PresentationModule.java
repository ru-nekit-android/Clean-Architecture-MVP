package ru.nekit.android.clean_architecture.presentation.internal.di.modules;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.domain.interactors.ObtainRepositoriesInteractor;
import ru.nekit.android.clean_architecture.presentation.model.GithubModel;
import ru.nekit.android.clean_architecture.presentation.model.IGithubModel;
import ru.nekit.android.clean_architecture.presentation.presenter.RepositoryListPresenter;
import ru.nekit.android.clean_architecture.presentation.presenter.mapper.RepositoryToModelMapper;
import ru.nekit.android.clean_architecture.presentation.navigation.NavigationCommand;
import ru.nekit.android.clean_architecture.presentation.navigation.qualifier.NavigateToRepositoryInfo;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
public class PresentationModule {

    @Singleton
    @Provides
    @NonNull
    public RepositoryListPresenter provideRepositoryListPresenter(@NonNull IGithubModel model,
                                                                  @NonNull ObtainRepositoriesInteractor interactor,
                                                                  @NonNull RepositoryToModelMapper mapper) {
        return new RepositoryListPresenter(model, interactor, mapper);
    }

    @Singleton
    @Provides
    @NonNull
    public IGithubModel provideIGithubModel() {
        return new GithubModel();
    }

    @Singleton
    @Provides
    @NonNull
    @NavigateToRepositoryInfo
    public NavigationCommand provideNavigationCommand() {
        return () -> {
            //no-op
        };
    }
}
