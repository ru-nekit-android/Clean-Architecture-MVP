package ru.nekit.android.mvpmeeting.model;

import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.base.IMVPModel;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
public interface IGithubModel extends IMVPModel {

    ApiInterface getApiInterface();

}
