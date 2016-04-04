package ru.nekit.android.clean_architecture.presentation.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.GithubApp;
import ru.nekit.android.clean_architecture.presentation.core.view.fragment.MVPBaseFragment;
import ru.nekit.android.clean_architecture.presentation.navigation.NavigationToLogcatCommand;
import ru.nekit.android.clean_architecture.presentation.navigation.qualifier.NavigateToLogcat;
import ru.nekit.android.clean_architecture.presentation.presenter.DeveloperSettingsPresenter;

public class DeveloperSettingsFragment extends MVPBaseFragment<DeveloperSettingsPresenter> implements IDeveloperSettingsView {

    @Inject
    protected DeveloperSettingsPresenter mPresenter;

    @Bind(R.id.developer_settings_git_sha_text_view)
    TextView gitShaTextView;

    @Bind(R.id.developer_settings_build_date_text_view)
    TextView buildDateTextView;

    @Inject
    @NavigateToLogcat
    protected NavigationToLogcatCommand logcatNavigation;

    public DeveloperSettingsFragment() {
        //empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GithubApp.get(getContext()).applicationComponent().developerSettingsComponent().inject(this);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_developer_settings, container, false);
    }

    @Override
    protected DeveloperSettingsPresenter getPresenter() {
        return mPresenter;
    }

    @OnClick(R.id.show_log)
    public void onShowLogcatButtonClick() {
        getPresenter().showLogcat();
    }

    @Override
    public void showLogcat() {
        logcatNavigation.setParentActivity(getActivity());
        logcatNavigation.navigate();
    }

    @Override
    public void updateGitSha(String value) {
        gitShaTextView.setText(value);
    }

    @Override
    public void updateBuildDate(String value) {
        buildDateTextView.setText(value);
    }
}
