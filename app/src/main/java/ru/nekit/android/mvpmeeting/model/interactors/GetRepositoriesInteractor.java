package ru.nekit.android.mvpmeeting.model.interactors;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.data.RepositoryDTO;
import ru.nekit.android.mvpmeeting.model.interactors.base.IInteractor;
import ru.nekit.android.mvpmeeting.utils.rx.RxTransformers;
import rx.Observable;

/**
 * Created by ru.nekit.android on 07.03.16.
 */
public class GetRepositoriesInteractor implements IInteractor<List<RepositoryDTO>> {

    @Inject
    protected ApiInterface apiInterface;
    private String name;

    @Singleton
    @Inject
    public GetRepositoriesInteractor() {

    }

    public void setUserName(String name) {
        this.name = name;
    }

    public Observable<List<RepositoryDTO>> execute() {
        return apiInterface.getRepositories(name).compose(RxTransformers.applySchedulers());
    }
}
