package ru.nekit.android.clean_architecture.data.repository;

import java.util.List;

import javax.inject.Inject;

import ru.nekit.android.clean_architecture.data.api.di.GithubModule;
import ru.nekit.android.clean_architecture.data.mapper.RepositoryEntityToRepositoryMapper;
import ru.nekit.android.clean_architecture.data.repository.di.qualifier.LongOperationThread;
import ru.nekit.android.clean_architecture.data.repository.di.qualifier.MainThread;
import ru.nekit.android.clean_architecture.data.utils.rx.RxTransformers;
import ru.nekit.android.clean_architecture.domain.Repository;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by ru.nekit.android on 15.03.16.
 */
public class GithubRepository implements IGithubRepository {

    private final GithubModule.Api mGitHubApi;
    private final Scheduler mSubscribeOnThread;
    private final Scheduler mObserveOnThread;
    private final RepositoryEntityToRepositoryMapper mMapper;

    @Inject
    public GithubRepository(GithubModule.Api gitHubApiService,
                            RepositoryEntityToRepositoryMapper mapper,
                            @LongOperationThread Scheduler subscribeOnThread,
                            @MainThread Scheduler observeOnThread) {
        mGitHubApi = gitHubApiService;
        mSubscribeOnThread = subscribeOnThread;
        mObserveOnThread = observeOnThread;
        mMapper = mapper;
    }

    @Override
    public Observable<List<Repository>> getRepositories(final String userName) {
        return mGitHubApi.getRepositories(userName).map(mMapper)
                .compose(RxTransformers.applySchedulers(mSubscribeOnThread, mObserveOnThread));
    }
}
