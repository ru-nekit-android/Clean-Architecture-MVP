package ru.nekit.android.mvpmeeting.model;

import java.util.List;

import ru.nekit.android.mvpmeeting.model.data.Repo;
import rx.Observable;

/**
 * Created by MacOS on 02.03.16.
 */
public interface IModel {

    Observable<List<Repo>> getRepoList(String name);

}
