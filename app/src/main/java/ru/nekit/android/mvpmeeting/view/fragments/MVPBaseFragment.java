package ru.nekit.android.mvpmeeting.view.fragments;

import android.support.v4.app.Fragment;

import ru.nekit.android.mvpmeeting.presenter.MVPBasePresenter;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public abstract class MVPBaseFragment extends Fragment {

    protected abstract MVPBasePresenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }
}
