package ru.nekit.android.mvpmeeting.view.fragments.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import ru.nekit.android.mvpmeeting.presenter.base.IMVPPresenter;
import ru.nekit.android.mvpmeeting.view.base.IMVPView;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public abstract class MVPBaseFragment<P extends IMVPPresenter, V extends IMVPView> extends Fragment implements IMVPView {

    protected abstract P getPresenter();

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
        if (getPresenter() != null) {
            getPresenter().attachView(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getPresenter() != null) {
            getPresenter().onSaveInstanceState(outState);
        }
    }
}
