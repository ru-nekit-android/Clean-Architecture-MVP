package ru.nekit.android.clean_architecture.presentation.developer_settings;

import android.support.annotation.NonNull;

import dagger.Subcomponent;
import ru.nekit.android.clean_architecture.presentation.view.fragments.DeveloperSettingsFragment;

/**
 * Created by ru.nekit.android on 30.03.16.
 */
@Subcomponent
public interface DeveloperSettingsComponent {
    void inject(@NonNull DeveloperSettingsFragment value);
}
