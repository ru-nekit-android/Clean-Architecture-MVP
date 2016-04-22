package ru.nekit.android.clean_architecture.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.nekit.android.clean_architecture.data.GithubApiTest;
import ru.nekit.android.clean_architecture.data.GithubRepositoryTest;
import ru.nekit.android.clean_architecture.data.di.api.GithubModule;
import ru.nekit.android.clean_architecture.data.di.network.NetworkModule;
import ru.nekit.android.clean_architecture.data.di.network.OkHttpInterceptorsModule;
import ru.nekit.android.clean_architecture.di.modules.TestApplicationModule;
import ru.nekit.android.clean_architecture.di.modules.TestDataModule;
import ru.nekit.android.clean_architecture.di.modules.TestSupportModule;
import ru.nekit.android.clean_architecture.presentation.developer_settings.DeveloperSettingsModule;
import ru.nekit.android.clean_architecture.presentation.di.ApplicationComponent;

/**
 * Created by ru.nekit.android on 20.04.16.
 */
@Singleton
@Component(
        modules = {
                TestApplicationModule.class,
                TestDataModule.class,
                GithubModule.class,
                NetworkModule.class,
                OkHttpInterceptorsModule.class,
                TestSupportModule.class,
                DeveloperSettingsModule.class
        }
)
public interface TestApplicationComponent extends ApplicationComponent {

    void inject(GithubApiTest value);

    void inject(GithubRepositoryTest value);

}
