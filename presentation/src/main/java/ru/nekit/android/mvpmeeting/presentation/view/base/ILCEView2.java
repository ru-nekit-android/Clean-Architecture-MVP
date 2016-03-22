package ru.nekit.android.mvpmeeting.presentation.view.base;

import ru.nekit.android.mvpmeeting.presentation.model.base.IModel;

/**
 * Created by ru.nekit.android on 06.03.16.
 */
public interface ILCEView2<M extends IModel, E> extends IMVPView {

    void showLoading();

    void hideLoading();

    void showContent(M content);

    void hideContent();

    void showError(E e);
}
