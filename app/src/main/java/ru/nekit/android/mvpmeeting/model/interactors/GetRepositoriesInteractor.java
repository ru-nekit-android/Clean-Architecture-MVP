package ru.nekit.android.mvpmeeting.model.interactors;

import java.util.List;

import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.data.RepositoryDTO;
import ru.nekit.android.mvpmeeting.utils.rx.RxTransformers;
import rx.Observable;

/**
 * Created by ru.nekit.android on 07.03.16.
 */
public class GetRepositoriesInteractor {


    private final ApiInterface apiInterface;

    public GetRepositoriesInteractor(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }


    public Observable<List<RepositoryDTO>> get(String name) {
        return apiInterface.getRepositories(name).compose(RxTransformers.applySchedulers());
    }
}
