package ru.nekit.android.mvpmeeting.presentation.presenter.base;

import android.os.Bundle;

import ru.nekit.android.mvpmeeting.presentation.model.base.IViewModel;
import ru.nekit.android.mvpmeeting.presentation.view.base.IMVPView;

/**
 * Created by ru.nekit.android on 06.03.16.
 */
public interface IMVPPresenter<V extends IMVPView, VM extends IViewModel> {

    void attachView(V view);

    void detachView();

    V getView();

    VM getViewModel();

    boolean isAttached();

    void onCreate(Bundle savedInstanceState);

    void onSaveInstanceState(Bundle bundle);

    void onDestroy();

}
