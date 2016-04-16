package ru.nekit.android.clean_architecture.presentation.core.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.nekit.android.clean_architecture.presentation.core.model.IMVPViewModel;
import ru.nekit.android.clean_architecture.presentation.core.view.IMVPView;

/**
 * Created by ru.nekit.android on 06.03.16.
 */
public interface IMVPPresenter<V extends IMVPView, VM extends IMVPViewModel> {

    void attachView(@NonNull V view);

    void detachView();

    @Nullable
    V getView();

    @Nullable
    VM getViewModel();

    void onCreate(@Nullable Bundle savedInstanceState);

    void onSaveInstanceState(@Nullable Bundle bundle);

    void onDestroy();

    void onAttachView();
}
