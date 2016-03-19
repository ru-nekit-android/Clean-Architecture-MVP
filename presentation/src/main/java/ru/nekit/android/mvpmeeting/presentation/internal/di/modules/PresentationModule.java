package ru.nekit.android.mvpmeeting.presentation.internal.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.mvpmeeting.domain.interactors.ObtainRepositoriesInteractor;
import ru.nekit.android.mvpmeeting.presentation.model.GithubViewModel;
import ru.nekit.android.mvpmeeting.presentation.presenter.RepositoryListPresenter;
import ru.nekit.android.mvpmeeting.presentation.presenter.mapper.RepositoryToModelMapper;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
public class PresentationModule {

    @Singleton
    @Provides
    public RepositoryListPresenter providePresenter(GithubViewModel viewModel, ObtainRepositoriesInteractor interactor, RepositoryToModelMapper mapper) {
        return new RepositoryListPresenter(viewModel, interactor, mapper);
    }

}
