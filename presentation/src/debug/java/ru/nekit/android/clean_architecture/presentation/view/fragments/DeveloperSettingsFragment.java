package ru.nekit.android.clean_architecture.presentation.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.GithubApp;
import ru.nekit.android.clean_architecture.presentation.presenter.DeveloperSettingsPresenter;
import ru.nekit.android.clean_architecture.presentation.navigation.NavigationToLogcatCommand;
import ru.nekit.android.clean_architecture.presentation.navigation.qualifier.NavigateToLogcat;
import ru.nekit.android.clean_architecture.presentation.view.fragments.base.MVPBaseFragment;

public class DeveloperSettingsFragment extends MVPBaseFragment<DeveloperSettingsPresenter> implements IDeveloperSettingsView {

    @Inject
    protected DeveloperSettingsPresenter mPresenter;

    @Inject
    @NavigateToLogcat
    protected NavigationToLogcatCommand logcatNavigation;

    public DeveloperSettingsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GithubApp.get(getContext()).applicationComponent().developerSettingsComponent().inject(this);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_developer_settings, container, false);
        ButterKnife.bind(this, view);
        return view;
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
}