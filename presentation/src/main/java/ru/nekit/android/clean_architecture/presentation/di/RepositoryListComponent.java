package ru.nekit.android.clean_architecture.presentation.di;

import android.support.annotation.NonNull;

import dagger.Subcomponent;
import ru.nekit.android.clean_architecture.presentation.core.navigation.NavigationCommand;
import ru.nekit.android.clean_architecture.presentation.core.presenter.IHasPresenter;
import ru.nekit.android.clean_architecture.presentation.di.modules.RepositoryListModule;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.NavigateToRepositoryInfo;
import ru.nekit.android.clean_architecture.presentation.di.scope.PerActivity;
import ru.nekit.android.clean_architecture.presentation.presenter.RepositoryListPresenter;

/**
 * Created by ru.nekit.android on 06.04.16.
 */
@Subcomponent(modules = {RepositoryListModule.class})
@PerActivity
public interface RepositoryListComponent extends IHasPresenter<RepositoryListPresenter> {

    @NonNull
    @NavigateToRepositoryInfo
    NavigationCommand getNavigateToRepositoryInfo();

}
