package ru.nekit.android.clean_architecture.presentation.core.presenter.viewState;

import android.support.annotation.NonNull;

/**
 * Created by ru.nekit.android on 18.03.16.
 */
public interface IStateable<T extends ViewState> {

    void applyViewState();

    @NonNull
    T getViewState();

    void setViewState(@NonNull T value);

}
