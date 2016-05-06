package ru.nekit.android.clean_architecture.presentation.presenter.mapper;

import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;
import ru.nekit.android.clean_architecture.domain.interactors.RequestRepositoryListUseCase;
import ru.nekit.android.clean_architecture.presentation.tools.BaseTest;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;
import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static rx.Observable.just;

public class RepositoryEntityToEntityVOMapperTest extends BaseTest {

    private static final String USER_NAME = "ru-nekit-android";
    @Mock
    private RequestRepositoryListUseCase interactor;

    //real
    private RepositoryEntityToVOMapper mapper;

    public void setUp() {
        initMocks(this);
        mapper = new RepositoryEntityToVOMapper();
    }

    public void tearDown() {
        //no-op
    }

    public Observable<List<RepositoryEntity>> provideRepositoryListObservable() {
        List<RepositoryEntity> repositoryEntityList = new ArrayList<>();
        repositoryEntityList.add(new RepositoryEntity(5301791, "abs-search-view", USER_NAME, "", "1", "1", "1"));
        repositoryEntityList.add(new RepositoryEntity(5528510, "GIS", USER_NAME, "", "1", "1", "1"));
        return just(repositoryEntityList);
    }

    @Test
    public void testRepositoryEntityToRepositoryVOMapper() {

        when(interactor.execute(anyString())).thenReturn(provideRepositoryListObservable());

        TestSubscriber<List<RepositoryVO>> subscriber = new TestSubscriber<>();
        interactor.execute(anyString()).map(mapper).subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);
        subscriber.assertCompleted();

        List<RepositoryVO> actual = subscriber.getOnNextEvents().get(0);
        assertNotNull(actual);
        assertEquals(2, actual.size());
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