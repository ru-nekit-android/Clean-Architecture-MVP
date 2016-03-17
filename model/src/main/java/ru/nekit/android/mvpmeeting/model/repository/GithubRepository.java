package ru.nekit.android.mvpmeeting.model.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

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
    private Scheduler mPreScheduler;
    private Scheduler mPostScheduler;
    private RepositoryEntityToRepositoryMapper mMapper;

    @Inject
    public GithubRepository(GitHubApiService gitHubApiService, RepositoryEntityToRepositoryMapper mapper, @IOThread Scheduler preScheduler, @UIThread Scheduler postScheduler) {
        mGitHubApiService = gitHubApiService;
        mPreScheduler = preScheduler;
        mPostScheduler = postScheduler;
        mMapper = mapper;
    }

    @Override
    public Observable<List<Repository>> getRepositories(final String userName) {
        return mGitHubApiService.getRepositories(userName).map(mMapper).compose(RxTransformers.applySchedulers(mPreScheduler, mPostScheduler));
    }
}
