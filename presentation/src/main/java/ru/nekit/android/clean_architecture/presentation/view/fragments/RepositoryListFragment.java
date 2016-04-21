package ru.nekit.android.clean_architecture.presentation.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.GithubApplication;
import ru.nekit.android.clean_architecture.presentation.di.RepositoryListComponent;
import ru.nekit.android.clean_architecture.presentation.di.modules.RepositoryListModule;
import ru.nekit.android.clean_architecture.presentation.di.scope.RepositoryListScope;
import ru.nekit.android.clean_architecture.presentation.model.IRepositoryListViewModel;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;
import ru.nekit.android.clean_architecture.presentation.presenter.RepositoryListPresenter;
import ru.nekit.android.clean_architecture.presentation.view.adapters.RepositoryListAdapter;

@RepositoryListScope
public final class RepositoryListFragment extends BaseFragment<RepositoryListComponent, RepositoryListPresenter> implements IRepositoryListView {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.user_name_input)
    EditText userNameInput;

    @Bind(R.id.message_view)
    TextView messageView;

    @Bind(R.id.progress)
    ProgressBar progressView;

    private LinearLayoutManager mLinearLayout;
    private RepositoryListAdapter mAdapter;

    public static RepositoryListFragment newInstance() {
        return new RepositoryListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_repository_list, container, false);
        mLinearLayout = new LinearLayoutManager(getActivity().getApplicationContext());
        mAdapter = new RepositoryListAdapter(getPresenter());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(mLinearLayout);
        recyclerView.setAdapter(mAdapter);
        messageView.setText(getString(R.string.result_is_empty));
    }

    @OnClick(R.id.search_repositories_button)
    protected void onSearchClick() {
        getPresenter().onSearchClick();
    }

    private void makeToast(String text) {
        Snackbar.make(recyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public String getUserName() {
        return userNameInput.getText().toString();
    }

    @Override
    public void showEmptyList() {
        messageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRepository(RepositoryVO repository) {
        //pmd
    }

    @Override
    public void showLoading() {
        messageView.setVisibility(View.INVISIBLE);
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showContent(IRepositoryListViewModel content) {
        messageView.setVisibility(View.INVISIBLE);
        mAdapter.setRepositoryList(content.getRepositoriesList());
        hideSoftKeyboard();
    }

    @Override
    public void hideContent() {
        messageView.setVisibility(View.VISIBLE);
        mAdapter.setRepositoryList(null);
    }

    @Override
    public void showError(Throwable error) {
        makeToast(error.getMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GithubApplication.get(getContext()).getApplicationComponent().leakCanaryProxy().watch(this);
    }

    @Override
    public void onDestroyView() {
        mAdapter.destroy();
        super.onDestroyView();
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(userNameInput.getWindowToken(), 0);
    }

    @Override
    protected RepositoryListComponent onCreateNonConfigurationComponent() {
        return getApplicationComponent().plus(new RepositoryListModule());
    }
}