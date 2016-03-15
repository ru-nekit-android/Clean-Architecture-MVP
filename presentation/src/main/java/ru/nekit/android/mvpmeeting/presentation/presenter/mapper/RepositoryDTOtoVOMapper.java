package ru.nekit.android.mvpmeeting.presentation.presenter.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.nekit.android.mvpmeeting.model.RepositoryDTO;
import ru.nekit.android.mvpmeeting.presentation.presenter.mapper.base.BaseMapper;
import ru.nekit.android.mvpmeeting.presentation.presenter.vo.RepositoryVO;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
@Singleton
public class RepositoryDTOtoVOMapper extends BaseMapper<RepositoryDTO, RepositoryVO> {

    @Inject
    public RepositoryDTOtoVOMapper() {
    }

    public RepositoryVO convert(RepositoryDTO value) {
        return new RepositoryVO(value.getName(), value.getOwner().getLogin());
    }

}
