package ru.nekit.android.clean_architecture.presentation.core.presenter;

/**
 * Created by ru.nekit.android on 14.04.16.
 */
public interface IHasPresenter<P extends IMVPPresenter> {

    P getPresenter();
}
