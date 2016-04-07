package ru.nekit.android.clean_architecture.presentation.di;

import dagger.Subcomponent;
import ru.nekit.android.clean_architecture.presentation.di.modules.RepositoryListModule;
import ru.nekit.android.clean_architecture.presentation.di.scope.PerActivity;
import ru.nekit.android.clean_architecture.presentation.view.activities.RepositoryListActivity;
import ru.nekit.android.clean_architecture.presentation.view.fragments.RepositoryListFragment;

/**
 * Created by ru.nekit.android on 06.04.16.
 */
@Subcomponent(modules = {RepositoryListModule.class})
@PerActivity
public interface RepositoryListComponent {
    void inject(RepositoryListFragment value);

    void inject(RepositoryListActivity value);
}
