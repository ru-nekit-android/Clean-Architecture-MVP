package ru.nekit.android.mvpmeeting.presentation.view.fragments;

import ru.nekit.android.mvpmeeting.presentation.model.base.IViewModel;
import ru.nekit.android.mvpmeeting.presentation.model.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.presentation.view.base.IStateableLceView;
import ru.nekit.android.mvpmeeting.presentation.view.base.ViewState;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public interface IRepositoryListView<M extends IViewModel, E, S extends IStateableLceView.State> extends IStateableLceView<M, E, S> {

    String getUserName();

    void showEmptyList();

    void showRepository(RepositoryVO repository);
}

