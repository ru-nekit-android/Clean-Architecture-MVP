package ru.nekit.android.mvpmeeting.presentation.view.base;

import ru.nekit.android.mvpmeeting.presentation.model.base.IViewModel;

/**
 * Created by MacOS on 02.03.16.
 */
public interface IMVPView<M extends IViewModel> {

    M getViewModel();

}
