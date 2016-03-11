package ru.nekit.android.mvpmeeting.model;

import java.util.List;

import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.api.ApiModule;
import ru.nekit.android.mvpmeeting.presenter.vo.Repository;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class GithubModel implements IGithubModel {

    private ApiInterface mApiInterface = ApiModule.getApiInterface();
    private List<Repository> mRepositoryList;

    @Override
    public void setRepositoryList(List<Repository> list) {
        mRepositoryList = list;
    }

    @Override
    public List<Repository> getRepositoryList() {
        return mRepositoryList;
    }

    @Override
    public ApiInterface getApiInterface() {
        return mApiInterface;
    }
}
