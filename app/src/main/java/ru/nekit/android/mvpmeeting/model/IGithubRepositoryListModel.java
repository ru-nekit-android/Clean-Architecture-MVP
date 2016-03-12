package ru.nekit.android.mvpmeeting.model;

import java.util.List;

import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.base.IMVPModel;
import ru.nekit.android.mvpmeeting.presenter.vo.Repository;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
public interface IGithubRepositoryListModel extends IMVPModel {

    void setRepositoryList(List<Repository> list);
    List<Repository> getRepositoryList();
}
