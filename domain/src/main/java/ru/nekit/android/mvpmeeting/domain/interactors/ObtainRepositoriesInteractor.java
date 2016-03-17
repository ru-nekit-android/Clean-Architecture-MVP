package ru.nekit.android.mvpmeeting.domain.interactors;

import java.util.List;

import ru.nekit.android.mvpmeeting.domain.Repository;
import ru.nekit.android.mvpmeeting.domain.interactors.base.IInteractor;
import ru.nekit.android.mvpmeeting.domain.repository.IGithubRepository;
import rx.Observable;

/**
 * Created by ru.nekit.android on 07.03.16.
 */
public class ObtainRepositoriesInteractor implements IInteractor<List<Repository>> {

    private IGithubRepository mRepository;
    private String mName;

    public ObtainRepositoriesInteractor(IGithubRepository repository) {
        mRepository = repository;
    }

    public void setUserName(String name) {
        mName = name;
    }

    public Observable<List<Repository>> execute() {
        return mRepository.getRepositories(mName);
    }
}
