package ru.nekit.android.mvpmeeting.model;

import java.util.List;

import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.api.ApiModule;
import ru.nekit.android.mvpmeeting.model.data.RepositoryDTO;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MacOS on 02.03.16.
 */
public class Model implements IModel {

    ApiInterface apiInterface = ApiModule.getApiInterface();

    @Override
    public Observable<List<RepositoryDTO>> getRepoList(String name) {
        return apiInterface.getRepositories(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
