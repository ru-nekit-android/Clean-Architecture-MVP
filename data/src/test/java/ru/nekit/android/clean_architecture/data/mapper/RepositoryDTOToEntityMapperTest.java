package ru.nekit.android.clean_architecture.data.mapper;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import ru.nekit.android.clean_architecture.data.BaseTest;
import ru.nekit.android.clean_architecture.data.entities.RepositoryDTO;
import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import rx.Observable;
import rx.observers.TestSubscriber;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static rx.Observable.just;

public class RepositoryDTOToEntityMapperTest extends BaseTest {

    @Mock
    protected IGithubRepository githubRepository;
    @Mock
    protected Observable<List<RepositoryDTO>> listObservable;

    //real
    private RepositoryDTOToEntityMapper mapper;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mapper = new RepositoryDTOToEntityMapper();
    }

    @Override
    public void tearDown() {
        //no-op
    }

    public Observable<List<RepositoryDTO>> provideRepositoryListObservable() {
        return just(asList(testUtils.getGson().fromJson(testUtils.readString("json/repos"), RepositoryDTO[].class)));
    }

    @Test
    public void testRepositoryEntityToRepositoryVOMapper() {
        when(githubRepository.getRepositories(anyString())).thenReturn(provideRepositoryListObservable().map(mapper));
        TestSubscriber<List<RepositoryEntity>> subscriber = new TestSubscriber<>();
        githubRepository.getRepositories(anyString()).subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);
        subscriber.assertCompleted();

        List<RepositoryEntity> actual = subscriber.getOnNextEvents().get(0);
        assertNotNull(actual);
        assertEquals(30, actual.size());
        //first
        RepositoryEntity entity = actual.get(0);
        assertEquals(entity.getName(), "abs-search-view");
        assertTrue(entity.getId() == 5301791);
        //last
        entity = actual.get(actual.size() - 1);
        assertEquals(entity.getName(), "GIS");
        assertTrue(entity.getId() == 5528510);
    }

}