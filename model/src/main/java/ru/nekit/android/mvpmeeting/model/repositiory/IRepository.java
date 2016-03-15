package ru.nekit.android.mvpmeeting.model.repositiory;

import java.util.List;

import ru.nekit.android.mvpmeeting.model.RepositoryDTO;
import rx.Observable;

/**
 * Created by MacOS on 15.03.16.
 */
public interface IRepository {

    Observable<List<RepositoryDTO>> getRepositoryList(String userName);

}
