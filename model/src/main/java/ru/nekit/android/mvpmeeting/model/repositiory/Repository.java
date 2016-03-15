package ru.nekit.android.mvpmeeting.model.repositiory;

import java.util.List;

/*import javax.inject.Inject;
import javax.inject.Named;

import ru.nekit.android.mvpmeeting.model.Const;*/
import ru.nekit.android.mvpmeeting.model.RepositoryDTO;
import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.utils.rx.RxTransformers;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by MacOS on 15.03.16.
 */
public class Repository implements IRepository {

    private ApiInterface mApiInterface;
    private Scheduler mPreScheduler;
    private Scheduler mPostScheduler;

    //@Inject
    public Repository(ApiInterface apiInterface, /*@Named(Const.PRE_THREAD)*/ Scheduler preScheduler, /*@Named(Const.POST_THREAD)*/ Scheduler postScheduler) {
        mApiInterface = apiInterface;
        mPreScheduler = preScheduler;
        mPostScheduler = postScheduler;
    }

    @Override
    public Observable<List<RepositoryDTO>> getRepositoryList(String userName) {
        return mApiInterface.getRepositories(userName).compose(RxTransformers.applySchedulers(mPreScheduler, mPostScheduler));
    }
}
