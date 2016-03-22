package ru.nekit.android.mvpmeeting.presentation.view.fragments;

import ru.nekit.android.mvpmeeting.presentation.model.IGithubModel;
import ru.nekit.android.mvpmeeting.presentation.model.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.presentation.view.base.ILCEView2;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public interface IRepositoryListView extends ILCEView2<IGithubModel, Throwable> {

    String getUserName();

    void showEmptyList();

    void showRepository(RepositoryVO repository);
}

