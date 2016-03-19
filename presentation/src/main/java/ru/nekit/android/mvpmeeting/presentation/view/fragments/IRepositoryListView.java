package ru.nekit.android.mvpmeeting.presentation.view.fragments;

import ru.nekit.android.mvpmeeting.presentation.model.IGithubViewModel;
import ru.nekit.android.mvpmeeting.presentation.model.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.presentation.view.base.ILCEView;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public interface IRepositoryListView extends ILCEView<IGithubViewModel, Throwable> {

    String getUserName();

    void showEmptyList();

    void showRepository(RepositoryVO repository);
}

