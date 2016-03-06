package ru.nekit.android.mvpmeeting.view.fragments.base;

import android.support.v4.app.Fragment;

import ru.nekit.android.mvpmeeting.presenter.base.MVPBasePresenter;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public abstract class MVPBaseFragment extends Fragment {

    protected abstract MVPBasePresenter getPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().onDestroy();
        }
    }
}
