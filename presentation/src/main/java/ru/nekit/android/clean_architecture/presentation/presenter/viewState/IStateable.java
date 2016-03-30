package ru.nekit.android.clean_architecture.presentation.presenter.viewState;

/**
 * Created by ru.nekit.android on 18.03.16.
 */
public interface IStateable<T extends ViewState> {

    void applyState();

}
