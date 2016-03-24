package ru.nekit.android.mvpmeeting.model.repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import ru.nekit.android.mvpmeeting.domain.Repository;
import ru.nekit.android.mvpmeeting.domain.repository.IGithubRepository;
import ru.nekit.android.mvpmeeting.model.internal.di.qualifier.IOThread;
import ru.nekit.android.mvpmeeting.model.internal.di.qualifier.UIThread;
import ru.nekit.android.mvpmeeting.model.mapper.RepositoryEntityToRepositoryMapper;
import ru.nekit.android.mvpmeeting.model.net.GitHubApiService;
import ru.nekit.android.mvpmeeting.model.utils.rx.RxTransformers;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by MacOS on 15.03.16.
 */
public class GithubRepository implements IGithubRepository {

    private GitHubApiService mGitHubApiService;
    private Scheduler mSubscribeOnThread;
    private Scheduler mObserveOnThread;
    private RepositoryEntityToRepositoryMapper mMapper;

    @Inject
    public GithubRepository(GitHubApiService gitHubApiService,
                            RepositoryEntityToRepositoryMapper mapper,
                            @IOThread Scheduler subscribeOnThread,
                            @UIThread Scheduler observeOnThread) {
        mGitHubApiService = gitHubApiService;
        mSubscribeOnThread = subscribeOnThread;
        mObserveOnThread = observeOnThread;
        mMapper = mapper;
    }

    @Override
    public Observable<List<Repository>> getRepositories(final String userName) {
        return mGitHubApiService.getRepositories(userName).map(mMapper)
                .compose(RxTransformers.applySchedulers(mSubscribeOnThread, mObserveOnThread));
    }
}
