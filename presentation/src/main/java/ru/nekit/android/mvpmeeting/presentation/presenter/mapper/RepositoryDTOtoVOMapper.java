package ru.nekit.android.mvpmeeting.presentation.presenter.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.nekit.android.mvpmeeting.data.RepositoryDTO;
import ru.nekit.android.mvpmeeting.presentation.presenter.mapper.base.BaseMapper;
import ru.nekit.android.mvpmeeting.presentation.presenter.vo.Repository;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
@Singleton
public class RepositoryDTOtoVOMapper extends BaseMapper<RepositoryDTO, Repository> {

    @Inject
    public RepositoryDTOtoVOMapper() {
    }

    public Repository convert(RepositoryDTO value) {
        return new Repository(value.getName(), value.getOwner().getLogin());
    }

}
