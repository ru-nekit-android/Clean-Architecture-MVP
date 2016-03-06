package ru.nekit.android.mvpmeeting.view.fragments.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import ru.nekit.android.mvpmeeting.presenter.base.MVPBasePresenter;
import ru.nekit.android.mvpmeeting.view.base.IMVPView;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public abstract class MVPBaseFragment extends Fragment implements IMVPView {

    protected abstract MVPBasePresenter getPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().onDestroy();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getPresenter().attachView(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
    }
}
