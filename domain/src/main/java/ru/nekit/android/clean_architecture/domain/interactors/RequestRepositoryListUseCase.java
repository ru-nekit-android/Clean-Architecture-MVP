package ru.nekit.android.clean_architecture.domain.interactors;

import java.util.List;

import ru.nekit.android.clean_architecture.domain.core.interactors.IInteractor;
import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import rx.Observable;
import rx.Subscription;
import rx.subjects.ReplaySubject;

/**
 * Created by ru.nekit.android on 07.03.16.
 */
public class RequestRepositoryListUseCase implements IInteractor<List<RepositoryEntity>, String> {

    private final IGithubRepository mRepository;
    private ReplaySubject<List<RepositoryEntity>> mDelegateSubject;
    private Subscription mDelegateSubscription;

    public RequestRepositoryListUseCase(IGithubRepository repository) {
        mRepository = repository;
    }

    public Observable<List<RepositoryEntity>> execute(String name) {
        if (mDelegateSubscription == null || mDelegateSubscription.isUnsubscribed()) {
            mDelegateSubject = ReplaySubject.create();
            mDelegateSubscription = mRepository.getRepositories(name).subscribe(mDelegateSubject);
        }
        return mDelegateSubject.asObservable();
    }
}
