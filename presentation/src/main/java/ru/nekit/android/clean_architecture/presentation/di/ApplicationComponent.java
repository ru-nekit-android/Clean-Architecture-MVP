package ru.nekit.android.clean_architecture.presentation.di;

import android.app.Application;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import ru.nekit.android.clean_architecture.data.di.api.GithubModule;
import ru.nekit.android.clean_architecture.data.di.network.NetworkModule;
import ru.nekit.android.clean_architecture.data.di.network.OkHttpInterceptorsModule;
import ru.nekit.android.clean_architecture.presentation.developer_settings.DeveloperSettingsComponent;
import ru.nekit.android.clean_architecture.presentation.developer_settings.DeveloperSettingsModule;
import ru.nekit.android.clean_architecture.presentation.developer_settings.ILeakCanaryProxy;
import ru.nekit.android.clean_architecture.presentation.di.modules.ApplicationModule;
import ru.nekit.android.clean_architecture.presentation.di.modules.DataModule;
import ru.nekit.android.clean_architecture.presentation.di.modules.RepositoryListModule;
import ru.nekit.android.clean_architecture.presentation.view.other.IViewModifier;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                DataModule.class,
                GithubModule.class,
                NetworkModule.class,
                OkHttpInterceptorsModule.class,
                DeveloperSettingsModule.class
        }
)
public interface ApplicationComponent {

    void inject(@NonNull Application value);

    @NonNull
    DeveloperSettingsComponent developerSettingsComponent();

    @NonNull
    RepositoryListComponent plus(@NonNull RepositoryListModule module);

    @NonNull
    ILeakCanaryProxy leakCanaryProxy();

    @NonNull
    IViewModifier viewModifier();
}
