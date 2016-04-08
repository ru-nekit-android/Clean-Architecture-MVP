package ru.nekit.android.clean_architecture.presentation.view.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.core.view.activity.MVPBaseActivity;
import ru.nekit.android.clean_architecture.presentation.di.HasComponent;
import ru.nekit.android.clean_architecture.presentation.di.RepositoryListComponent;
import ru.nekit.android.clean_architecture.presentation.di.modules.RepositoryListModule;
import ru.nekit.android.clean_architecture.presentation.view.fragments.RepositoryListFragment;
import ru.nekit.android.clean_architecture.presentation.view.other.IViewModifier;

public class RepositoryListActivity extends MVPBaseActivity implements HasComponent<RepositoryListComponent> {

    private static final String TAG = "fragmentTag";

    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Inject
    protected IViewModifier viewModifier;

    private FragmentManager fragmentManager;
    private RepositoryListComponent repositoryListComponent;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for inject IViewModifier viewModifier
        initializeInjector().inject(this);
        
        setContentView(viewModifier.modify(getLayoutInflater().inflate(R.layout.activity_main, null)));

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) {
            replaceFragment(RepositoryListFragment.newInstance(), false);
        }
    }

    @NonNull
    private RepositoryListComponent initializeInjector() {
        repositoryListComponent = getApplicationComponent().plus(new RepositoryListModule());
        return repositoryListComponent;
    }

    private void replaceFragment(@NonNull Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    @NonNull
    public RepositoryListComponent getComponent() {
        return repositoryListComponent;
    }
}
