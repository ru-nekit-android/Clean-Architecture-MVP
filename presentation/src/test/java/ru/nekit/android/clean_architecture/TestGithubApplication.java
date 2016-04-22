package ru.nekit.android.clean_architecture;

import ru.nekit.android.clean_architecture.di.DaggerTestApplicationComponent;
import ru.nekit.android.clean_architecture.di.modules.TestApplicationModule;
import ru.nekit.android.clean_architecture.presentation.GithubApplication;
import ru.nekit.android.clean_architecture.presentation.di.ApplicationComponent;

/**
 * Created by ru.nekit.android on 20.04.16.
 */
public class TestGithubApplication extends GithubApplication {

    @Override
    protected ApplicationComponent prepareApplicationComponent() {
        return DaggerTestApplicationComponent.builder().testApplicationModule(new TestApplicationModule(this)).build();
    }

}
