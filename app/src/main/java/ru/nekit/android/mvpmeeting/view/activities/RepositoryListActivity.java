package ru.nekit.android.mvpmeeting.view.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.nekit.android.mvpmeeting.GithubApp;
import ru.nekit.android.mvpmeeting.R;
import ru.nekit.android.mvpmeeting.presenter.vo.Repository;
import ru.nekit.android.mvpmeeting.view.fragments.RepositoryListFragment;

public class RepositoryListActivity extends AppCompatActivity implements RepositoryListFragment.ActivityCallback {

    private static final String TAG = "fragmentTag";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();

        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) {
            RepositoryListFragment repositoryListFragment = RepositoryListFragment.newInstance();
            GithubApp.getComponent().inject(repositoryListFragment);
            replaceFragment(repositoryListFragment, false);
        }
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    public void showRepositoryInfoFragment(Repository repository) {

    }
}
