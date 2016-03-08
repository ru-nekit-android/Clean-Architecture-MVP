package ru.nekit.android.mvpmeeting.presenter.base;

import android.os.Bundle;

import ru.nekit.android.mvpmeeting.model.base.IMVPModel;
import ru.nekit.android.mvpmeeting.view.base.IMVPView;

/**
 * Created by ru.nekit.android on 06.03.16.
 */
public interface IMVPPresenter<V extends IMVPView, M extends IMVPModel> {

    void attachView(V view);

    void detachView();

    V getView();

    M getModel();

    boolean isAttached();

    void onCreate(Bundle savedInstanceState);

    void onSaveInstanceState(Bundle bundle);

    void onDestroy();

}
