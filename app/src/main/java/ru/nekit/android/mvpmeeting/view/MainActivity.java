package ru.nekit.android.mvpmeeting.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.nekit.android.mvpmeeting.R;
import ru.nekit.android.mvpmeeting.model.data.Repo;
import ru.nekit.android.mvpmeeting.presenter.Presenter;
import ru.nekit.android.mvpmeeting.view.adapters.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements IView, View.OnClickListener {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.user_name_input)
    EditText userNameInput;
    @Bind(R.id.obtain_repos_button)
    Button obtainReposButton;
    private Presenter presenter;
    private RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        presenter = new Presenter(this);
        obtainReposButton.setOnClickListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public String getUserName() {
        return userNameInput.getText().toString();
    }

    @Override
    public void showData(List<Repo> data) {
        adapter.setRepoList(data);
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
    public void onClick(View view) {
        if (R.id.obtain_repos_button == view.getId()) {
            presenter.onSearchClick();
        }
    }

    private void makeToast(String text) {
        Snackbar.make(toolbar, text, Snackbar.LENGTH_LONG).show();
    }
}
