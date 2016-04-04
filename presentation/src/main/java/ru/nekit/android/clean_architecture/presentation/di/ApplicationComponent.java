package ru.nekit.android.clean_architecture.presentation.di;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import ru.nekit.android.clean_architecture.presentation.GithubApp;
import ru.nekit.android.clean_architecture.presentation.developer_settings.DeveloperSettingsComponent;
import ru.nekit.android.clean_architecture.presentation.developer_settings.ILeakCanaryProxy;
import ru.nekit.android.clean_architecture.presentation.di.modules.ApplicationModule;
import ru.nekit.android.clean_architecture.presentation.di.modules.DataModule;
import ru.nekit.android.clean_architecture.presentation.di.modules.DomainModule;
import ru.nekit.android.clean_architecture.presentation.di.modules.PresentationModule;
import ru.nekit.android.clean_architecture.presentation.view.activities.RepositoryListActivity;
import ru.nekit.android.clean_architecture.presentation.view.fragments.RepositoryListFragment;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                PresentationModule.class,
                DomainModule.class,
                DataModule.class
        }
)
public interface ApplicationComponent {

    void inject(@NonNull GithubApp value);

    void inject(@NonNull RepositoryListActivity value);

    void inject(@NonNull RepositoryListFragment value);

    @NonNull
    ILeakCanaryProxy leakCanaryProxy();

    @NonNull
    DeveloperSettingsComponent developerSettingsComponent();

}