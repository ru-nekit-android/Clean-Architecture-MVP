package ru.nekit.android.mvpmeeting.model;

import java.util.List;

import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.api.ApiModule;
import ru.nekit.android.mvpmeeting.model.data.RepositoryDTO;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class GithubModel implements IMVPModel {

    ApiInterface apiInterface = ApiModule.getApiInterface();

    public Observable<List<RepositoryDTO>> getRepoList(String name) {
        return apiInterface.getRepositories(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
