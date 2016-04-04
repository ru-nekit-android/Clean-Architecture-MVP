package ru.nekit.android.clean_architecture.presentation.core.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import ru.nekit.android.clean_architecture.presentation.core.model.IMVPModel;
import ru.nekit.android.clean_architecture.presentation.core.view.IMVPView;

/**
 * Created by ru.nekit.android on 06.03.16.
 */
public interface IMVPPresenter<V extends IMVPView, M extends IMVPModel> {

    void attachView(@NonNull V view);

    void detachView();

    V getView();

    M getModel();

    boolean isViewAttached();

    void onCreate(Bundle savedInstanceState);

    void onSaveInstanceState(Bundle bundle);

    void onDestroy();

}
