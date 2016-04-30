package ru.nekit.android.clean_architecture.data.repository;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import ru.nekit.android.clean_architecture.data.BaseTest;
import ru.nekit.android.clean_architecture.data.api.GithubApi;
import ru.nekit.android.clean_architecture.data.entities.RepositoryDTO;
import ru.nekit.android.clean_architecture.data.mapper.RepositoryDTOToEntityMapper;
import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by ru.nekit.android on 22.04.16.
 */
public class GithubRepositoryTest extends BaseTest {

    @Mock
    protected GithubApi githubApi;

    private GithubRepository githubRepository;

    @Override
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        githubRepository = new GithubRepository(githubApi, new RepositoryDTOToEntityMapper(), Schedulers.immediate(), Schedulers.immediate());
    }

    @Override
    public void tearDown() {
        //no-op
    }

    public Observable<List<RepositoryDTO>> provideRepositoryListObservable() {
        List<RepositoryDTO> repositoryDTOs = Arrays.asList(testUtils.readJson("json/repos", RepositoryDTO[].class));
        return Observable.just(repositoryDTOs);
    }

    @Test
    public void testGetRepositoryList() {

        when(githubApi.getRepositories(anyString())).thenReturn(provideRepositoryListObservable());

        TestSubscriber<List<RepositoryEntity>> subscriber = new TestSubscriber<>();
        githubRepository.getRepositories(anyString()).subscribe(subscriber);

        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);
        subscriber.assertCompleted();

        List<RepositoryEntity> actual = subscriber.getOnNextEvents().get(0);
        Assert.assertNotNull(actual);
        Assert.assertEquals(30, actual.size());
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
