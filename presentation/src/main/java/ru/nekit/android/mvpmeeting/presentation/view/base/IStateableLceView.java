package ru.nekit.android.mvpmeeting.presentation.view.base;

import ru.nekit.android.mvpmeeting.presentation.model.base.IViewModel;

/**
 * Created by ru.nekit.android on 06.03.16.
 */
public interface  IStateableLceView<M extends IViewModel, E, S extends IStateableLceView.State> extends ILceView<M, E>, IStateableView<S> {

    enum State implements ViewState {
        EMPTY,
        CONTENT,
        LOADING,
        ERROR
    }

}
