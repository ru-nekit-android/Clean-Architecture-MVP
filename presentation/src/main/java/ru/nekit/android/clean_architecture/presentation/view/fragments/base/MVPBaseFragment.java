package ru.nekit.android.clean_architecture.presentation.view.fragments.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import ru.nekit.android.clean_architecture.presentation.presenter.base.IMVPPresenter;
import ru.nekit.android.clean_architecture.presentation.view.base.IMVPView;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public abstract class MVPBaseFragment<P extends IMVPPresenter> extends Fragment implements IMVPView {

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        if (getPresenter() != null) {
            getPresenter().attachView(this);
            getPresenter().onCreate(savedInstanceState);
        }
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
        ButterKnife.unbind(this);
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
