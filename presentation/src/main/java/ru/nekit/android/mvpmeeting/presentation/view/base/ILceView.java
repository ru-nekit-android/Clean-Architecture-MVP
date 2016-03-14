package ru.nekit.android.mvpmeeting.presentation.view.base;

/**
 * Created by ru.nekit.android on 06.03.16.
 */
public interface ILceView<D, E> extends IMVPView {

    void showLoading();

    void hideLoading();

    void showContent();

    void setData(D data);

    void showError(E e);
}
