package ru.nekit.android.mvpmeeting.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.nekit.android.mvpmeeting.R;
import ru.nekit.android.mvpmeeting.presenter.RepositoryListPresenter;
import ru.nekit.android.mvpmeeting.presenter.vo.Repository;
import ru.nekit.android.mvpmeeting.view.adapters.RecyclerViewAdapter;

public class RepositoryListActivity extends AppCompatActivity implements IView {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.user_name_input)
    EditText userNameInput;
    @Bind(R.id.obtain_repos_button)
    Button obtainReposButton;
    private RepositoryListPresenter presenter;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        presenter = new RepositoryListPresenter(this);

        obtainReposButton.setOnClickListener(view -> presenter.onSearchClick());

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        presenter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public String getUserName() {
        return userNameInput.getText().toString();
    }

    @Override
    public void showRepositoryList(List<Repository> repositoryList) {
        adapter.setRepoList(repositoryList);
    }

    @Override
    public void showEmptyList() {
        makeToast(getString(R.string.result_is_empty));
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (presenter != null) {
            presenter.onSaveInstanceState(outState);
        }
    }

    private void makeToast(String text) {
        Snackbar.make(toolbar, text, Snackbar.LENGTH_LONG).show();
    }
}
