package ru.nekit.android.clean_architecture.presentation.presenter.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.nekit.android.clean_architecture.data.mapper.base.BaseMapper;
import ru.nekit.android.clean_architecture.domain.Repository;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
@Singleton
public class RepositoryToModelMapper extends BaseMapper<Repository, RepositoryVO> {

    @Inject
    public RepositoryToModelMapper() {
    }

    public RepositoryVO convert(Repository value) {
        return new RepositoryVO(value.name, value.ownerName, value.description, Integer.toString(value.startCount), Integer.toString(value.forks), Integer.toString(value.watchers));
    }

}
