package ru.nekit.android.clean_architecture.presentation.model;

import ru.nekit.android.clean_architecture.presentation.core.model.IMVPViewModel;

/**
 * Created by ru.nekit.android on 30.03.16.
 */
public interface IDeveloperSettingsViewModel extends IMVPViewModel {

    String gitSha();

    String buildDate();
}
