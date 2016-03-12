package ru.nekit.android.mvpmeeting.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.nekit.android.mvpmeeting.R;
import ru.nekit.android.mvpmeeting.model.IGithubRepositoryListModel;
import ru.nekit.android.mvpmeeting.presenter.RepositoryListPresenter;
import ru.nekit.android.mvpmeeting.presenter.vo.Repository;
import ru.nekit.android.mvpmeeting.view.adapters.RepositoryListAdapter;
import ru.nekit.android.mvpmeeting.view.fragments.base.MVPBaseFragment;

public class RepositoryListFragment extends MVPBaseFragment<RepositoryListPresenter, IRepositoryListView> implements IRepositoryListView {

    @Bind(R.id.recyclerView)
    protected RecyclerView recyclerView;

    @Bind(R.id.user_name_input)
    protected EditText userNameInput;

    @Bind(R.id.obtain_repositories_button)
    protected Button obtainRepositoriesButton;

    @Bind(R.id.message_view)
    protected TextView messageView;

    @Inject
    protected RepositoryListPresenter mPresenter;

    private RepositoryListAdapter mAdapter;
    private IGithubRepositoryListModel mData;
    private ActivityCallback mCallback;

    public static RepositoryListFragment newInstance() {
        return new RepositoryListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repository_list, container, false);
        ButterKnife.bind(this, view);

        obtainRepositoriesButton.setOnClickListener(v -> mPresenter.onSearchClick());

        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(llm);
        mAdapter = new RepositoryListAdapter(mPresenter);
        recyclerView.setAdapter(mAdapter);

        mPresenter.onCreate(savedInstanceState);
        return view;
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

    @Override
    public void showError(Throwable error) {
        makeToast(error.getMessage());
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
        messageView.setText(getString(R.string.result_is_empty));
    }

    @Override
    public void showRepository(Repository repository) {
        mCallback.showRepositoryInfoFragment(repository);
    }

    public void showLoading() {
        messageView.setText(getString(R.string.loading_message));
    }

    public void hideLoading() {
        messageView.setText("");
    }

    public void showContent() {
        mAdapter.setRepositoryList(mData.getRepositoryList());
    }

    public void setData(IGithubRepositoryListModel data) {
        mData = data;
    }

    public interface ActivityCallback {
        void showRepositoryInfoFragment(Repository repository);
    }
}