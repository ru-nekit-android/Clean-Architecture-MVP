package ru.nekit.android.mvpmeeting.presenter.mapper;

import java.util.List;

import ru.nekit.android.mvpmeeting.model.data.RepositoryDTO;
import ru.nekit.android.mvpmeeting.presenter.vo.Repository;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by MacOS on 04.03.16.
 */
public class RepositoryListMapper implements Func1<List<RepositoryDTO>, List<Repository>> {
    @Override
    public List<Repository> call(List<RepositoryDTO> repositoryDTOs) {

        List<Repository> repoList = Observable.from(repositoryDTOs)
                .map(repoDTO -> new Repository(repoDTO.getName(), repoDTO.getOwner().getLogin()))
                .toList()
                .toBlocking()
                .first();
        return repoList;

    }
}
