package ru.nekit.android.clean_architecture.data.mapper;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.nekit.android.clean_architecture.data.core.mapper.BaseMapper;
import ru.nekit.android.clean_architecture.data.entities.RepositoryEntity;
import ru.nekit.android.clean_architecture.domain.Repository;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
@Singleton
public final class RepositoryEntityToRepositoryMapper extends BaseMapper<RepositoryEntity, Repository> {

    @Inject
    public RepositoryEntityToRepositoryMapper() {
        //empty constructor for injection
    }

    public Repository convert(@NonNull RepositoryEntity entity) {
        return new Repository(entity.getName(), entity.getOwner().getLogin(), entity.getDescription(), entity.getStargazersCount(), entity.getForks(), entity.getWatchers());
    }

}
