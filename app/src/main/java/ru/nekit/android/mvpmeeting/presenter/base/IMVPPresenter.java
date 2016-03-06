package ru.nekit.android.mvpmeeting.presenter.base;

import android.os.Bundle;

import ru.nekit.android.mvpmeeting.view.base.IMVPView;

/**
 * Created by ru.nekit.android on 06.03.16.
 */
public interface IMVPPresenter<V extends IMVPView> {

    void attachView(V view);

    void detachView();

    boolean isAttached();

    void onCreate(Bundle savedInstanceState);

    void onSaveInstanceState(Bundle bundle);

    void onDestroy();

}
