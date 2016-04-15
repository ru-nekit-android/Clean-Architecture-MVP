package ru.nekit.android.clean_architecture.presentation.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.developer_settings.DeveloperSettingsComponent;
import ru.nekit.android.clean_architecture.presentation.presenter.DeveloperSettingsPresenter;

public class DeveloperSettingsFragment extends BaseFragment<DeveloperSettingsComponent, DeveloperSettingsPresenter> implements IDeveloperSettingsView {


    @Bind(R.id.developer_settings_git_sha_text_view)
    TextView gitShaTextView;

    @Bind(R.id.developer_settings_build_date_text_view)
    TextView buildDateTextView;

    public DeveloperSettingsFragment() {
        //empty constructor
    }

    @Override
    @NonNull
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_developer_settings, container, false);
    }

    @OnClick(R.id.show_log)
    public void onShowLogcatButtonClick() {
        getPresenter().showLogcat();
    }

    @Override
    public void showLogcat() {
    }

    @Override
    public void updateGitSha(String value) {
        gitShaTextView.setText(value);
    }

    @Override
    public void updateBuildDate(String value) {
        buildDateTextView.setText(value);
    }

    @Override
    protected DeveloperSettingsComponent onCreateNonConfigurationComponent() {
        return getApplicationComponent().developerSettingsComponent();
    }

}
