package ru.nekit.android.mvpmeeting.presentation.presenter.base;

import android.os.Bundle;

import ru.nekit.android.mvpmeeting.presentation.model.base.IViewModel;
import ru.nekit.android.mvpmeeting.presentation.view.base.IMVPView;

/**
 * Created by ru.nekit.android on 06.03.16.
 */
public interface IMVPPresenter<V extends IMVPView, M extends IViewModel> {

    void attachView(V view);

    void detachView();

    V getView();

    M getModel();

    boolean isAttached();

    void onCreate(Bundle savedInstanceState);

    void onSaveInstanceState(Bundle bundle);

    void onDestroy();

}
