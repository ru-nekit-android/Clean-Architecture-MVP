package ru.nekit.android.mvpmeeting.presentation.presenter.viewState;

/**
 * Created by MacOS on 18.03.16.
 */
public interface IStateable<T extends ViewState> {

    void applyState();

}
