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

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.GithubApp;
import ru.nekit.android.clean_architecture.presentation.model.IGithubModel;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;
import ru.nekit.android.clean_architecture.presentation.navigation.NavigationCommand;
import ru.nekit.android.clean_architecture.presentation.navigation.qualifier.NavigateToRepositoryInfo;
import ru.nekit.android.clean_architecture.presentation.presenter.RepositoryListPresenter;
import ru.nekit.android.clean_architecture.presentation.view.adapters.RepositoryListAdapter;
import ru.nekit.android.clean_architecture.presentation.view.fragments.base.MVPBaseFragment;

public class RepositoryListFragment extends MVPBaseFragment<RepositoryListPresenter> implements IRepositoryListView {

    @Bind(R.id.recyclerView)
    protected RecyclerView recyclerView;

    @Bind(R.id.user_name_input)
    protected EditText userNameInput;

    @Bind(R.id.message_view)
    protected TextView messageView;

    @Bind(R.id.progress)
    protected ProgressBar progressView;

    @Inject
    protected RepositoryListPresenter mPresenter;

    @Inject
    @NavigateToRepositoryInfo
    protected NavigationCommand navigateToRepositoryInfo;

    private LinearLayoutManager llm;
    private RepositoryListAdapter mAdapter;

    public static RepositoryListFragment newInstance() {
        return new RepositoryListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GithubApp.get(getContext()).applicationComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(ru.nekit.android.clean_architecture.R.layout.fragment_repository_list, container, false);
        llm = new LinearLayoutManager(getActivity().getApplicationContext());
        mAdapter = new RepositoryListAdapter(getPresenter());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mAdapter);
        messageView.setText(getString(R.string.result_is_empty));
    }

    @OnClick(ru.nekit.android.clean_architecture.R.id.obtain_repositories_button)
    protected void onObtainClick() {
        mPresenter.onSearchClick();
    }

    @Override
    protected RepositoryListPresenter getPresenter() {
        return mPresenter;
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
        navigateToRepositoryInfo.navigate();
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
    public void showContent(IGithubModel content) {
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
    public void showError(Throwable e) {
        makeToast(e.getMessage());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GithubApp.get(getContext()).applicationComponent().leakCanaryProxy().watch(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAdapter.destroy();
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(userNameInput.getWindowToken(), 0);
    }
}