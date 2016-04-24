package ru.nekit.android.clean_architecture.data.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.nekit.android.clean_architecture.data.entities.RepositoryDTO;
import ru.nekit.android.clean_architecture.domain.core.mapper.BaseMapper;
import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
@Singleton
public final class RepositoryDTOToEntityMapper extends BaseMapper<RepositoryDTO, RepositoryEntity> {

    @Inject
    public RepositoryDTOToEntityMapper() {
        //empty constructor for injection
    }

    public RepositoryEntity convert(RepositoryDTO value) {
        return new RepositoryEntity(value.getId(),
                value.getName(),
                value.getOwner().getLogin(),
                value.getDescription(),
                Integer.toString(value.getStargazersCount()),
                Integer.toString(value.getForksCount()),
                Integer.toString(value.getWatchersCount()));
    }

}
