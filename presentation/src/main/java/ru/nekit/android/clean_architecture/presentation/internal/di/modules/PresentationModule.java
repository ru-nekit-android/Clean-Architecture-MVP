package ru.nekit.android.clean_architecture.presentation.internal.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.presentation.model.GithubModel;
import ru.nekit.android.clean_architecture.presentation.model.IGithubModel;
import ru.nekit.android.clean_architecture.presentation.presenter.RepositoryListPresenter;
import ru.nekit.android.clean_architecture.presentation.presenter.mapper.RepositoryToModelMapper;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
public class PresentationModule {

    @Singleton
    @Provides
    public RepositoryListPresenter provideRepositoryListPresenter(IGithubModel model, ru.nekit.android.clean_architecture.domain.interactors.ObtainRepositoriesInteractor interactor, RepositoryToModelMapper mapper) {
        return new RepositoryListPresenter(model, interactor, mapper);
    }

    @Singleton
    @Provides
    public IGithubModel provideIGithubModel() {
        return new GithubModel();
    }

}
