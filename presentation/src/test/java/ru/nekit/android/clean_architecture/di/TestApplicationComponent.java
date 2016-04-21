package ru.nekit.android.clean_architecture.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.nekit.android.clean_architecture.data.GithubApiTest;
import ru.nekit.android.clean_architecture.presentation.developer_settings.DeveloperSettingsModule;
import ru.nekit.android.clean_architecture.presentation.di.ApplicationComponent;
import ru.nekit.android.clean_architecture.presentation.di.modules.ApplicationModule;

/**
 * Created by ru.nekit.android on 20.04.16.
 */
@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                DeveloperSettingsModule.class
        }
)
public interface TestApplicationComponent extends ApplicationComponent {

    void inject(GithubApiTest value);

}
