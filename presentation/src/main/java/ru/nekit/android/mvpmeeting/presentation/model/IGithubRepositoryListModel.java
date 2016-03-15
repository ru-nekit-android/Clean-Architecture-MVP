package ru.nekit.android.mvpmeeting.presentation.model;

import java.util.List;

import ru.nekit.android.mvpmeeting.presentation.model.base.IMVPModel;
import ru.nekit.android.mvpmeeting.presentation.presenter.vo.RepositoryVO;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
public interface IGithubRepositoryListModel extends IMVPModel {

    void setRepositoryList(List<RepositoryVO> list);
    List<RepositoryVO> getRepositoryList();
}
