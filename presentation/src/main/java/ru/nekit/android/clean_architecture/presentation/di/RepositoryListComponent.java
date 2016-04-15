package ru.nekit.android.clean_architecture.presentation.di;

import dagger.Subcomponent;
import ru.nekit.android.clean_architecture.presentation.core.presenter.IHasPresenter;
import ru.nekit.android.clean_architecture.presentation.di.modules.RepositoryListModule;
import ru.nekit.android.clean_architecture.presentation.di.scope.RepositoryListScope;
import ru.nekit.android.clean_architecture.presentation.presenter.RepositoryListPresenter;

/**
 * Created by ru.nekit.android on 06.04.16.
 */
@Subcomponent(modules = {RepositoryListModule.class})
@RepositoryListScope
public interface RepositoryListComponent extends IHasPresenter<RepositoryListPresenter> {
}
