package ru.nekit.android.mvpmeeting.presenter.mapper;

import ru.nekit.android.mvpmeeting.model.data.RepositoryDTO;
import ru.nekit.android.mvpmeeting.presenter.vo.Repository;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
public class RepositoryListMapper extends BaseMapper<RepositoryDTO, Repository> {

    public Repository convert(RepositoryDTO value) {
        return new Repository(value.getName(), value.getOwner().getLogin());
    }

}
