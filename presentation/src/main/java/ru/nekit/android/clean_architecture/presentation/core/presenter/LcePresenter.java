package ru.nekit.android.clean_architecture.presentation.core.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.nekit.android.clean_architecture.presentation.core.model.IMVPViewModel;
import ru.nekit.android.clean_architecture.presentation.core.presenter.viewState.IStateable;
import ru.nekit.android.clean_architecture.presentation.core.presenter.viewState.LceViewState;
import ru.nekit.android.clean_architecture.presentation.core.view.IMVPView;

/**
 * Created by ru.nekit.android on 16.04.16.
 */
public abstract class LcePresenter<V extends IMVPView, VM extends IMVPViewModel> extends MVPPresenter<V, VM> implements IStateable<LceViewState> {

    @NonNull
    private LceViewState mState = LceViewState.EMPTY;

    protected LcePresenter(@Nullable VM model) {
        super(model);
    }

    @Override
    @NonNull
    public LceViewState getViewState() {
        return mState;
    }

    @Override
    public void setViewState(@NonNull LceViewState state) {
        mState = state;
    }
}
