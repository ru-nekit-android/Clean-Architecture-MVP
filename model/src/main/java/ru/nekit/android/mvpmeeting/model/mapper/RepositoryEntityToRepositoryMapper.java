package ru.nekit.android.mvpmeeting.model.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.nekit.android.mvpmeeting.domain.Repository;
import ru.nekit.android.mvpmeeting.model.entities.RepositoryEntity;
import ru.nekit.android.mvpmeeting.model.mapper.base.BaseMapper;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
@Singleton
public class RepositoryEntityToRepositoryMapper extends BaseMapper<RepositoryEntity, Repository> {

    @Inject
    public RepositoryEntityToRepositoryMapper() {
    }

    public Repository convert(RepositoryEntity value) {
        return new Repository(value.getName(), value.getOwner().getLogin());
    }

}
