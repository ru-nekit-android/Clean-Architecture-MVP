package ru.nekit.android.clean_architecture.presentation.di;

import dagger.Component;
import ru.nekit.android.clean_architecture.presentation.di.modules.ActivityModule;
import ru.nekit.android.clean_architecture.presentation.di.modules.RepositoryListModule;
import ru.nekit.android.clean_architecture.presentation.di.scope.PerActivity;
import ru.nekit.android.clean_architecture.presentation.view.fragments.RepositoryListFragment;

/**
 * Created by ru.nekit.android on 06.04.16.
 */
@PerActivity
    void inject(RepositoryListFragment value);
}
