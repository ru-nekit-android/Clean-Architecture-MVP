package ru.nekit.android.mvpmeeting.model;

import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.api.ApiModule;
import ru.nekit.android.mvpmeeting.model.base.IMVPModel;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class GithubModel implements IGithubModel {

    private ApiInterface mApiInterface = ApiModule.getApiInterface();

    public ApiInterface getApiInterface() {
        return mApiInterface;
    }

}
