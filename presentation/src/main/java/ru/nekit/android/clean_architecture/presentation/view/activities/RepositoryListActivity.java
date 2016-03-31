package ru.nekit.android.clean_architecture.presentation.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.GithubApp;
import ru.nekit.android.clean_architecture.presentation.view.fragments.RepositoryListFragment;
import ru.nekit.android.clean_architecture.presentation.view.other.IViewModifier;

public class RepositoryListActivity extends AppCompatActivity {

    private static final String TAG = "fragmentTag";

    @Nullable
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    IViewModifier viewModifier;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GithubApp.get(this).applicationComponent().inject(this);
        setContentView(viewModifier.modify(getLayoutInflater().inflate(R.layout.activity_main, null)));
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) {
            replaceFragment(RepositoryListFragment.newInstance(), false);
        }
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
