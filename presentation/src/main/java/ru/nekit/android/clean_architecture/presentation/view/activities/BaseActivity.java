package ru.nekit.android.clean_architecture.presentation.view.activities;

import ru.nekit.android.clean_architecture.presentation.GithubApplication;
import ru.nekit.android.clean_architecture.presentation.core.view.activity.MVPActivity;
import ru.nekit.android.clean_architecture.presentation.di.ApplicationComponent;

/**
 * Created by ru.nekit.android on 15.04.16.
 */
public class BaseActivity<C> extends MVPActivity<C> {

    public ApplicationComponent getApplicationComponent() {
        return GithubApplication.get(getApplicationContext()).getApplicationComponent();
    }

}
