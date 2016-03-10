package ru.nekit.android.mvpmeeting.model.interactors;

import java.util.List;

import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.data.RepositoryDTO;
import ru.nekit.android.mvpmeeting.model.interactors.base.IInteractor;
import ru.nekit.android.mvpmeeting.utils.rx.RxTransformers;
import rx.Observable;

/**
 * Created by ru.nekit.android on 07.03.16.
 */
public class GetRepositoriesInteractor implements IInteractor<List<RepositoryDTO>> {

    private final ApiInterface apiInterface;
    private final String name;

    public GetRepositoriesInteractor(ApiInterface apiInterface, String name) {
        this.apiInterface = apiInterface;
        this.name = name;
    }

    public Observable<List<RepositoryDTO>> execute() {
        return apiInterface.getRepositories(name).compose(RxTransformers.applySchedulers());
    }
}
