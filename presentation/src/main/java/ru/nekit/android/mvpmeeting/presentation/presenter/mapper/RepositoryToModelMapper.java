package ru.nekit.android.mvpmeeting.presentation.presenter.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.nekit.android.mvpmeeting.domain.Repository;
import ru.nekit.android.mvpmeeting.model.mapper.base.BaseMapper;
import ru.nekit.android.mvpmeeting.presentation.presenter.vo.RepositoryVO;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
@Singleton
public class RepositoryToModelMapper extends BaseMapper<Repository, RepositoryVO> {

    @Inject
    public RepositoryToModelMapper() {
    }

    public RepositoryVO convert(Repository value) {
        return new RepositoryVO(value.getRepoName(), value.getOwnerName());
    }

}
