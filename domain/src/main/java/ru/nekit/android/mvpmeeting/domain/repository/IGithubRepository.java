package ru.nekit.android.mvpmeeting.domain.repository;

import java.util.List;

import ru.nekit.android.mvpmeeting.domain.Repository;
import rx.Observable;

/**
 * Created by MacOS on 15.03.16.
 */
public interface IGithubRepository {

    Observable<List<Repository>> getRepositories(final String userName);

}
