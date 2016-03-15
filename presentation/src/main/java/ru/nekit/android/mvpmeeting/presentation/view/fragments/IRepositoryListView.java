package ru.nekit.android.mvpmeeting.presentation.view.fragments;

import ru.nekit.android.mvpmeeting.presentation.model.IGithubRepositoryListModel;
import ru.nekit.android.mvpmeeting.presentation.presenter.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.presentation.view.base.ILceView;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public interface IRepositoryListView extends ILceView<IGithubRepositoryListModel, Throwable> {

    String getUserName();

    void showEmptyList();

    void showRepository(RepositoryVO repository);
}
