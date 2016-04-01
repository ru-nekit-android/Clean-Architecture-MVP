package ru.nekit.android.clean_architecture.presentation.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import ru.nekit.android.clean_architecture.presentation.model.IDeveloperSettingsModel;
import ru.nekit.android.clean_architecture.presentation.presenter.base.MVPBasePresenter;
import ru.nekit.android.clean_architecture.presentation.view.fragments.IDeveloperSettingsView;

/**
 * Created by ru.nekit.android on 30.03.16.
 */
public class DeveloperSettingsPresenter extends MVPBasePresenter<IDeveloperSettingsView, IDeveloperSettingsModel> {

    public DeveloperSettingsPresenter(@NonNull IDeveloperSettingsModel model) {
        super(model);
    }

    @Override
    public IDeveloperSettingsModel getModel() {
        return model;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        IDeveloperSettingsView view = getView();
        view.updateGitSha(model.gitSha());
        view.updateBuildDate(model.buildDate());
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        //no-op
    }

    public void showLogcat() {
        getView().showLogcat();
    }
}
