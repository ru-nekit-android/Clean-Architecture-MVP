package ru.nekit.android.mvpmeeting.model;

import javax.inject.Inject;

import ru.nekit.android.mvpmeeting.GithubApp;
import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.api.ApiModule;
import ru.nekit.android.mvpmeeting.model.base.IMVPModel;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class GithubModel implements IGithubModel {

    @Inject
    protected ApiInterface mApiInterface;

    public GithubModel() {
        GithubApp.getComponent().inject(this);
    }

    public ApiInterface getApiInterface() {
        return mApiInterface;
    }

}
