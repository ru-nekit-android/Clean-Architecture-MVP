package ru.nekit.android.mvpmeeting.presentation.model;

import java.util.List;

import javax.inject.Inject;

import ru.nekit.android.mvpmeeting.presentation.presenter.vo.RepositoryVO;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class GithubRepositoryListModel implements IGithubRepositoryListModel {

    private List<RepositoryVO> mRepositoryList;

    @Inject
    public GithubRepositoryListModel() {
    }

    @Override
    public void setRepositoryList(List<RepositoryVO> value) {
        mRepositoryList = value;
    }

    @Override
    public List<RepositoryVO> getRepositoryList() {
        return mRepositoryList;
    }
}
