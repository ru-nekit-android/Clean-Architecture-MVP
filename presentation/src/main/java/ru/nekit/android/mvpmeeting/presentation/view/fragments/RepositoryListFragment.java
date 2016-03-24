package ru.nekit.android.mvpmeeting.presentation.view.fragments;

import android.content.Context;
import android.os.Bundle;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.nekit.android.mvpmeeting.R;
import ru.nekit.android.mvpmeeting.presentation.GithubApp;
import ru.nekit.android.mvpmeeting.presentation.model.IGithubModel;
import ru.nekit.android.mvpmeeting.presentation.model.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.presentation.presenter.RepositoryListPresenter;
import ru.nekit.android.mvpmeeting.presentation.view.adapters.RepositoryListAdapter;
import ru.nekit.android.mvpmeeting.presentation.view.fragments.base.MVPBaseFragment;

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

    private RepositoryListAdapter mAdapter;

    private ActivityCallback mCallback;

    public static RepositoryListFragment newInstance() {
        return new RepositoryListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GithubApp.getApplicationComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_repository_list, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(llm);

        mAdapter = new RepositoryListAdapter(getPresenter());
        recyclerView.setAdapter(mAdapter);

        messageView.setText(getString(R.string.result_is_empty));

        return view;
    }

    @OnClick(R.id.obtain_repositories_button)
    protected void onObtainClick() {
        mPresenter.onSearchClick();
    }

    @Override
    protected RepositoryListPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ActivityCallback) {
            mCallback = (ActivityCallback) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ActivityCallback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
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
        mCallback.showRepositoryInfoFragment(repository);
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
        GithubApp.getRefWatcher().watch(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAdapter.destroy();
        ButterKnife.unbind(this);
    }

    public interface ActivityCallback {
        void showRepositoryInfoFragment(RepositoryVO repository);
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(userNameInput.getWindowToken(), 0);
    }
}