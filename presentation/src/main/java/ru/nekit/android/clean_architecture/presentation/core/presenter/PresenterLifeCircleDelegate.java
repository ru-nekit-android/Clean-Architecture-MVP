package ru.nekit.android.clean_architecture.presentation.core.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.nekit.android.clean_architecture.presentation.core.view.IMVPView;

/**
 * Created by ru.nekit.android on 14.04.16.
 */
public class PresenterLifeCircleDelegate<P extends IMVPPresenter> {

    private P presenter;
    private boolean isDestroyedBySystem;

    public void onCreate(@NonNull P presenter, @Nullable Bundle savedInstanceState) {
        this.presenter = presenter;
        presenter.onCreate(savedInstanceState);
    }

    @SuppressWarnings("unchecked")
    public void onCreateView(@NonNull IMVPView view, Bundle savedInstanceState) {
        presenter.attachView(view);
        presenter.onAttachView(view);
    }

    public void onResume() {
        isDestroyedBySystem = false;
    }

    public void onSaveInstanceState(@Nullable Bundle outState) {
        isDestroyedBySystem = true;
        presenter.onSaveInstanceState(outState);
    }

    public void onDestroyView() {
        presenter.detachView();
    }

    public void onDestroy() {
        if (!isDestroyedBySystem) {
            presenter.onDestroy();
        }
    }
}
