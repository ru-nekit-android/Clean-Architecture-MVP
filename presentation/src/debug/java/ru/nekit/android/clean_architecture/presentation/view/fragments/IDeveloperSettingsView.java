package ru.nekit.android.clean_architecture.presentation.view.fragments;

import ru.nekit.android.clean_architecture.presentation.core.view.IMVPView;

/**
 * Created by ru.nekit.android on 30.03.16.
 */
public interface IDeveloperSettingsView extends IMVPView {

    void showLogcat();

    void updateGitSha(String value);

    void updateBuildDate(String value);
}
