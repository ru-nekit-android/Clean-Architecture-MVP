package ru.nekit.android.mvpmeeting.model;

import javax.inject.Inject;

import ru.nekit.android.mvpmeeting.model.api.ApiInterface;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class GithubModel implements IGithubModel {

    @Inject
    protected ApiInterface mApiInterface;

    @Inject
    public GithubModel() {
    }

    public ApiInterface getApiInterface() {
        return mApiInterface;
    }

}
