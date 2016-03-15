package ru.nekit.android.mvpmeeting.domain.interactors;

import java.util.List;

import ru.nekit.android.mvpmeeting.domain.interactors.base.IInteractor;
import ru.nekit.android.mvpmeeting.model.RepositoryDTO;
import ru.nekit.android.mvpmeeting.model.repositiory.IRepository;
import rx.Observable;

/**
 * Created by ru.nekit.android on 07.03.16.
 */
public class ObtainRepositoriesInteractor implements IInteractor<List<RepositoryDTO>> {

    private IRepository mRepository;
    private String name;

    public ObtainRepositoriesInteractor(IRepository repository) {
        mRepository = repository;
    }

    public void setUserName(String value) {
        name = value;
    }

    public Observable<List<RepositoryDTO>> execute() {
        return mRepository.getRepositoryList(name);
    }
}
