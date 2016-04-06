package ru.nekit.android.clean_architecture.data.repository;

import android.support.annotation.NonNull;

import java.util.List;

import ru.nekit.android.clean_architecture.data.di.api.GithubModule;
import ru.nekit.android.clean_architecture.data.mapper.RepositoryEntityToRepositoryMapper;
import ru.nekit.android.clean_architecture.data.utils.rx.RxTransformers;
import ru.nekit.android.clean_architecture.domain.Repository;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by ru.nekit.android on 15.03.16.
 */
public class GithubRepository implements IGithubRepository {

    private final GithubModule.Api mGithubApi;
    private final Scheduler mLongOperationThread;
    private final Scheduler mMainThread;
    private final RepositoryEntityToRepositoryMapper mMapper;

    public GithubRepository(GithubModule.Api githubApi, RepositoryEntityToRepositoryMapper mapper,
                            Scheduler longOperationThread,
                            Scheduler mainThread) {
        mGithubApi = githubApi;
        mLongOperationThread = longOperationThread;
        mMainThread = mainThread;
        mMapper = mapper;
    }

    @Override
    @NonNull
    public Observable<List<Repository>> getRepositories(final String userName) {
        return mGithubApi.getRepositories(userName).map(mMapper)
                .compose(RxTransformers.applySchedulers(mLongOperationThread, mMainThread));
    }
}
