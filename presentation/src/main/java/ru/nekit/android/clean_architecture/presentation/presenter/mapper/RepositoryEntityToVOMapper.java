package ru.nekit.android.clean_architecture.presentation.presenter.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.nekit.android.clean_architecture.domain.core.mapper.BaseMapper;
import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
@Singleton
public /*final*/ class RepositoryEntityToVOMapper extends BaseMapper<RepositoryEntity, RepositoryVO> {

    @Inject
    public RepositoryEntityToVOMapper() {
        //empty constructor for injection
    }

    public RepositoryVO convert(RepositoryEntity value) {
        return new RepositoryVO(value.getId(),
                value.getName(),
                value.getOwnerName(),
                value.getDescription(),
                value.getStarsCount(),
                value.getForksCount(),
                value.getWatchersCount());
    }

}
