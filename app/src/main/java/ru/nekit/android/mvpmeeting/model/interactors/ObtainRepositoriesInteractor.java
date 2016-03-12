package ru.nekit.android.mvpmeeting.model.interactors;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import ru.nekit.android.mvpmeeting.Const;
import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.data.RepositoryDTO;
import ru.nekit.android.mvpmeeting.model.interactors.base.IInteractor;
import ru.nekit.android.mvpmeeting.utils.rx.RxTransformers;
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

    @Singleton
    @Inject
    public ObtainRepositoriesInteractor(ApiInterface apiInterface, @Named(Const.PRE_THREAD) Scheduler preScheduler, @Named(Const.POST_THREAD) Scheduler postScheduler) {
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
