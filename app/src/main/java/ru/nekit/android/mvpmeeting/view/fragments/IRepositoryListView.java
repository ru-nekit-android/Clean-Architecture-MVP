package ru.nekit.android.mvpmeeting.view.fragments;

import java.util.List;

import ru.nekit.android.mvpmeeting.model.IGithubRepositoryListModel;
import ru.nekit.android.mvpmeeting.presenter.vo.Repository;
import ru.nekit.android.mvpmeeting.view.base.ILceView;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public interface IRepositoryListView extends ILceView<IGithubRepositoryListModel, Throwable> {

    String getUserName();

    void showEmptyList();

    void showRepository(Repository repository);
}
