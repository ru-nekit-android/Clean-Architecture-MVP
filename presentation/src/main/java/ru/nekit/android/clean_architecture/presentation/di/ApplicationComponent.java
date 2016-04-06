package ru.nekit.android.clean_architecture.presentation.di;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import ru.nekit.android.clean_architecture.presentation.GithubApplication;
import ru.nekit.android.clean_architecture.presentation.developer_settings.DeveloperSettingsComponent;
import ru.nekit.android.clean_architecture.presentation.developer_settings.DeveloperSettingsModule;
import ru.nekit.android.clean_architecture.presentation.developer_settings.ILeakCanaryProxy;
import ru.nekit.android.clean_architecture.presentation.di.modules.ApplicationModule;
import ru.nekit.android.clean_architecture.presentation.view.activities.RepositoryListActivity;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                DeveloperSettingsModule.class
        }
)
public interface ApplicationComponent {

    void inject(@NonNull GithubApplication value);

    @NonNull
    ILeakCanaryProxy leakCanaryProxy();

    @NonNull
    DeveloperSettingsComponent developerSettingsComponent();

    @NonNull
    IGithubRepository githubRepository();

    void inject(RepositoryListActivity value);
}
