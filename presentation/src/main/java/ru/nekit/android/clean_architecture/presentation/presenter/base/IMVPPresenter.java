package ru.nekit.android.clean_architecture.presentation.presenter.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import ru.nekit.android.clean_architecture.presentation.model.base.IModel;
import ru.nekit.android.clean_architecture.presentation.view.base.IMVPView;

/**
 * Created by ru.nekit.android on 06.03.16.
 */
public interface IMVPPresenter<V extends IMVPView, M extends IModel> {

    void attachView(@NonNull V view);

    void detachView();

    V getView();

    M getModel();

    boolean isViewAttached();

    void onCreate(Bundle savedInstanceState);

    void onSaveInstanceState(Bundle bundle);

    void onDestroy();

}
