package ru.nekit.android.mvpmeeting.domain.interactors;

import java.util.List;

import ru.nekit.android.mvpmeeting.data.RepositoryDTO;
import ru.nekit.android.mvpmeeting.data.api.ApiInterface;
import ru.nekit.android.mvpmeeting.data.utils.rx.RxTransformers;
import ru.nekit.android.mvpmeeting.domain.interactors.base.IInteractor;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by ru.nekit.android on 07.03.16.
 */
public class ObtainRepositoriesInteractor implements IInteractor<List<RepositoryDTO>> {

    private ApiInterface apiInterface;
    private Scheduler preScheduler;
    private Scheduler postScheduler;

    private String name;

    public ObtainRepositoriesInteractor(ApiInterface apiInterface, Scheduler preScheduler, Scheduler postScheduler) {
        this.apiInterface = apiInterface;
        this.preScheduler = preScheduler;
        this.postScheduler = postScheduler;
    }

    public void setUserName(String value) {
        name = value;
    }

    public Observable<List<RepositoryDTO>> execute() {
        return apiInterface.getRepositories(name).compose(RxTransformers.applySchedulers(preScheduler, postScheduler));
    }
}
