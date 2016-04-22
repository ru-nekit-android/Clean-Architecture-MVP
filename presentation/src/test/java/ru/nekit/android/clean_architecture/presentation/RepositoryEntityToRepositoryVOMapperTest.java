package ru.nekit.android.clean_architecture.presentation;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import ru.nekit.android.clean_architecture.core.BaseTest;
import ru.nekit.android.clean_architecture.di.TestRepositoryListComponent;
import ru.nekit.android.clean_architecture.di.modules.TestRepositoryListModule;
import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;
import ru.nekit.android.clean_architecture.domain.interactors.RequestRepositoryListUseCase;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.UserName;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;
import ru.nekit.android.clean_architecture.presentation.presenter.mapper.RepositoryEntityToRepositoryVOMapper;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class RepositoryEntityToRepositoryVOMapperTest extends BaseTest {

    @Inject
    @UserName
    protected String userName;
    @Inject
    protected RequestRepositoryListUseCase useCase;
    @Inject
    protected List<RepositoryEntity> repositoryEntities;

    //real
    private RepositoryEntityToRepositoryVOMapper mapper;

    @Override
    public void setUp() throws IOException {
        super.setUp();
        TestRepositoryListComponent component = testApplicationComponent.plus(new TestRepositoryListModule());
        component.inject(this);
        mapper = new RepositoryEntityToRepositoryVOMapper();
    }

    @Test
    public void testRepositoryEntityToRepositoryVOMapper() {
        when(useCase.execute(userName)).thenReturn(Observable.just(repositoryEntities));
        TestSubscriber<List<RepositoryVO>> subscriber = new TestSubscriber<>();
        useCase.execute(userName).map(mapper).subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);
        subscriber.assertCompleted();

        List<RepositoryVO> actual = subscriber.getOnNextEvents().get(0);
        Assert.assertNotNull(actual);
        Assert.assertEquals(30, actual.size());
        //first
        RepositoryVO entity = actual.get(0);
        assertEquals(entity.getRepoName(), "abs-search-view");
        assertTrue(entity.getId() == 5301791);
        //last
        entity = actual.get(actual.size() - 1);
        assertEquals(entity.getRepoName(), "GIS");
        assertTrue(entity.getId() == 5528510);
    }

}