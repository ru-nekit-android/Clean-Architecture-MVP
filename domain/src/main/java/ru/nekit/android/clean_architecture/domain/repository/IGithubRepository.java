package ru.nekit.android.clean_architecture.domain.repository;

import java.util.List;

import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;
import rx.Observable;

/**
 * Created by ru.nekit.android on 15.03.16.
 */
public interface IGithubRepository {

    Observable<List<RepositoryEntity>> getRepositories(final String userName);

}
