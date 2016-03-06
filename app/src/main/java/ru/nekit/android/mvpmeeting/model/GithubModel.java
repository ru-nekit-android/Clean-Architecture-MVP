package ru.nekit.android.mvpmeeting.model;

import java.util.List;

import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.api.ApiModule;
import ru.nekit.android.mvpmeeting.model.base.IMVPModel;
import ru.nekit.android.mvpmeeting.model.data.RepositoryDTO;
import ru.nekit.android.mvpmeeting.utils.rx.RxTransformers;
import rx.Observable;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class GithubModel implements IMVPModel {

    ApiInterface apiInterface = ApiModule.getApiInterface();

    public Observable<List<RepositoryDTO>> getRepoList(String name) {
        return apiInterface.getRepositories(name).compose(RxTransformers.applySchedulers());
    }
}
