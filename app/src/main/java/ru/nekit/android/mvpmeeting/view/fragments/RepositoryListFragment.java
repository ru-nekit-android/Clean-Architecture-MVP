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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.nekit.android.mvpmeeting.R;
import ru.nekit.android.mvpmeeting.presenter.MVPBasePresenter;
import ru.nekit.android.mvpmeeting.presenter.RepositoryListPresenter;
import ru.nekit.android.mvpmeeting.presenter.vo.Repository;
import ru.nekit.android.mvpmeeting.view.adapters.RepositoryListAdapter;

public class RepositoryListFragment extends MVPBaseFragment implements IRepositoryListView {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.user_name_input)
    EditText userNameInput;

    @Bind(R.id.obtain_repositories_button)
    Button obtainRepositoriesButton;

    private RepositoryListPresenter presenter;

    private RepositoryListAdapter adapter;

    private ActivityCallback mCallback;

    public RepositoryListFragment() {
    }

    public static RepositoryListFragment newInstance() {
        RepositoryListFragment fragment = new RepositoryListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_repository_list, container, false);
        ButterKnife.bind(this, view);

        presenter = new RepositoryListPresenter(this);

        obtainRepositoriesButton.setOnClickListener(v -> presenter.onSearchClick());

        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(llm);
        adapter = new RepositoryListAdapter(presenter);
        recyclerView.setAdapter(adapter);

        presenter.onLoadState(savedInstanceState);
        return view;
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
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (presenter != null) {
            presenter.onSaveState(outState);
        }
    }

    private void makeToast(String text) {
        Snackbar.make(recyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public String getUserName() {
        return userNameInput.getText().toString();
    }

    @Override
    public void showRepositoryList(List<Repository> repositoryList) {
        adapter.setRepositoryList(repositoryList);
    }

    @Override
    public void showEmptyList() {
        makeToast(getString(R.string.result_is_empty));
    }

    @Override
    public void showRepository(Repository repository) {
        mCallback.showRepositoryInfoFragment(repository);
    }

    @Override
    protected MVPBasePresenter getPresenter() {
        return presenter;
    }

    public interface ActivityCallback {
        void showRepositoryInfoFragment(Repository repository);
    }
}