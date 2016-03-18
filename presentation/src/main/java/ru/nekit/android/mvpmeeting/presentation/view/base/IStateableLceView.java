package ru.nekit.android.mvpmeeting.presentation.view.base;

import ru.nekit.android.mvpmeeting.presentation.model.base.IViewModel;

/**
 * Created by ru.nekit.android on 06.03.16.
 */
public interface IStateableLCEView<M extends IViewModel, E, S extends IStateableLCEView.LCEViewState> extends ILCEView<M, E>, IStateable<S> {

    enum LCEViewState implements ViewState {
        EMPTY,
        CONTENT,
        LOADING,
        ERROR
    }

}
