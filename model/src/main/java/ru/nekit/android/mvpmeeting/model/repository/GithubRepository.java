package ru.nekit.android.mvpmeeting.model.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ru.nekit.android.mvpmeeting.domain.Repository;
import ru.nekit.android.mvpmeeting.domain.repository.IGithubRepository;
import ru.nekit.android.mvpmeeting.model.Const;
import ru.nekit.android.mvpmeeting.model.mapper.RepositoryEntityToRepositoryMapper;
import ru.nekit.android.mvpmeeting.model.net.ApiInterface;
import ru.nekit.android.mvpmeeting.model.utils.rx.RxTransformers;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by MacOS on 15.03.16.
 */
public class GithubRepository implements IGithubRepository {

    private ApiInterface mApiInterface;
    private Scheduler mPreScheduler;
    private Scheduler mPostScheduler;
    private RepositoryEntityToRepositoryMapper mMapper;

    @Inject
    public GithubRepository(ApiInterface apiInterface, RepositoryEntityToRepositoryMapper mapper, @Named(Const.PRE_THREAD) Scheduler preScheduler, @Named(Const.POST_THREAD) Scheduler postScheduler) {
        mApiInterface = apiInterface;
        mPreScheduler = preScheduler;
        mPostScheduler = postScheduler;
        mMapper = mapper;
    }

    @Override
    public Observable<List<Repository>> getRepositories(final String userName) {
        return mApiInterface.getRepositories(userName).map(mMapper).compose(RxTransformers.applySchedulers(mPreScheduler, mPostScheduler));
    }
}
