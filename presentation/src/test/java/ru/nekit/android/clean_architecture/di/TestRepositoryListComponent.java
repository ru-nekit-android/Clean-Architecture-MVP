package ru.nekit.android.clean_architecture.di;

import dagger.Subcomponent;
import ru.nekit.android.clean_architecture.di.modules.TestRepositoryListModule;
import ru.nekit.android.clean_architecture.presentation.core.presenter.IHasPresenter;
import ru.nekit.android.clean_architecture.presentation.di.scope.RepositoryListScope;
import ru.nekit.android.clean_architecture.presentation.presenter.RepositoryListPresenter;

/**
 * Created by ru.nekit.android on 06.04.16.
 */
@Subcomponent(modules = {TestRepositoryListModule.class})
@RepositoryListScope
public interface TestRepositoryListComponent extends IHasPresenter<RepositoryListPresenter> {
}
