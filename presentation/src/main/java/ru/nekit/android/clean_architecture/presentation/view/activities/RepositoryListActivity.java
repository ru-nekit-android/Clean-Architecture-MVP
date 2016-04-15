package ru.nekit.android.clean_architecture.presentation.view.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.di.RepositoryListComponent;
import ru.nekit.android.clean_architecture.presentation.view.fragments.RepositoryListFragment;
import ru.nekit.android.clean_architecture.presentation.view.other.IViewModifier;

public class RepositoryListActivity extends BaseActivity<RepositoryListComponent> {

    private static final String TAG = "fragmentTag";

    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    protected IViewModifier viewModifier;

    private FragmentManager fragmentManager;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        viewModifier = getApplicationComponent().viewModifier();

        super.onCreate(savedInstanceState);

        setContentView(viewModifier.modify(getLayoutInflater().inflate(R.layout.activity_main, null)));

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) {
            replaceFragment(RepositoryListFragment.newInstance(), false);
        }
    }

    private void replaceFragment(@NonNull Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}
