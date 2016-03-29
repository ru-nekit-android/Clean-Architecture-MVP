package ru.nekit.android.clean_architecture.domain.repository;

import java.util.List;

import ru.nekit.android.clean_architecture.domain.Repository;
import rx.Observable;

/**
 * Created by MacOS on 15.03.16.
 */
public interface IGithubRepository {

    Observable<List<Repository>> getRepositories(final String userName);

}
