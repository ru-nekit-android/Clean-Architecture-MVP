package ru.nekit.android.mvpmeeting.view.fragments;

import java.util.List;

import ru.nekit.android.mvpmeeting.presenter.vo.Repository;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public interface IRepositoryListView extends IMVPView {

    String getUserName();

    void showRepositoryList(List<Repository> repositoryList);

    void showEmptyList();

    void showRepository(Repository repository);
}
