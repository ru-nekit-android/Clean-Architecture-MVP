package ru.nekit.android.mvpmeeting.presentation.view.fragments.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.nekit.android.mvpmeeting.presentation.presenter.base.IMVPPresenter;
import ru.nekit.android.mvpmeeting.presentation.view.base.IMVPView;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public abstract class MVPBaseFragment<P extends IMVPPresenter, V extends IMVPView> extends Fragment implements IMVPView {

    protected abstract P getPresenter();

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().onDestroy();
        }
    }

    @CallSuper
    @Override
    @SuppressWarnings("unchecked")
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().attachView(this);
        }
        return null;
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getPresenter() != null) {
            getPresenter().onSaveInstanceState(outState);
        }
    }
}
