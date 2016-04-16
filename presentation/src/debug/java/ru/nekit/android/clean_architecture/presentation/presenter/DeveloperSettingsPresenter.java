package ru.nekit.android.clean_architecture.presentation.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.nekit.android.clean_architecture.presentation.core.presenter.MVPPresenter;
import ru.nekit.android.clean_architecture.presentation.model.IDeveloperSettingsViewModel;
import ru.nekit.android.clean_architecture.presentation.view.fragments.IDeveloperSettingsView;

/**
 * Created by ru.nekit.android on 30.03.16.
 */
@Singleton
public class DeveloperSettingsPresenter extends MVPPresenter<IDeveloperSettingsView, IDeveloperSettingsViewModel> {

    @Inject
    public DeveloperSettingsPresenter(@NonNull IDeveloperSettingsViewModel model) {
        super(model);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //no-op
    }

    @Override
    public void onSaveInstanceState(@Nullable Bundle bundle) {
        //no-op
    }

    @Override
    public void onAttachView() {
        IDeveloperSettingsView view = getView();
        if (view != null) {
            view.updateGitSha(getViewModel().gitSha());
            view.updateBuildDate(getViewModel().buildDate());
        }
    }

    public void showLogcat() {
        IDeveloperSettingsView view = getView();
        if (view != null) {
            view.showLogcat();
        }
    }
}
