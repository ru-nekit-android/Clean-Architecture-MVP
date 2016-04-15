package ru.nekit.android.clean_architecture.presentation.view.fragments;

import ru.nekit.android.clean_architecture.presentation.GithubApplication;
import ru.nekit.android.clean_architecture.presentation.core.presenter.IHasPresenter;
import ru.nekit.android.clean_architecture.presentation.core.presenter.IMVPPresenter;
import ru.nekit.android.clean_architecture.presentation.core.view.fragment.MVPFragment;
import ru.nekit.android.clean_architecture.presentation.di.ApplicationComponent;

/**
 * Created by ru.nekit.android on 14.04.16.
 */
public abstract class BaseFragment<C extends IHasPresenter<P>, P extends IMVPPresenter> extends MVPFragment<C, P> {

    public ApplicationComponent getApplicationComponent() {
        return GithubApplication.get(getContext()).applicationComponent();
    }

}
