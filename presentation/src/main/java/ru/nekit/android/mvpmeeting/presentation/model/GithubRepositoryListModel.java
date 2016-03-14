package ru.nekit.android.mvpmeeting.presentation.model;

import java.util.List;

import javax.inject.Inject;

import ru.nekit.android.mvpmeeting.presentation.presenter.vo.Repository;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class GithubRepositoryListModel implements IGithubRepositoryListModel {

    private List<Repository> mRepositoryList;

    @Inject
    public GithubRepositoryListModel() {
    }

    @Override
    public void setRepositoryList(List<Repository> value) {
        mRepositoryList = value;
    }

    @Override
    public List<Repository> getRepositoryList() {
        return mRepositoryList;
    }
}
