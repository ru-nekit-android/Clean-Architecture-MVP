package ru.nekit.android.clean_architecture.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;
import ru.nekit.android.clean_architecture.domain.interactors.RequestRepositoryListUseCase;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static rx.Observable.just;

/**
 * Created by ru.nekit.android on 22.04.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class RequestRepositoryListUseCaseTest {

    private static final String USER_NAME = "ru-nekit-android";

    @Mock
    protected IGithubRepository githubRepository;

    //real
    private RequestRepositoryListUseCase interactor;

    @Before
    public void setUp() {
        interactor = new RequestRepositoryListUseCase(githubRepository);
    }

    public Observable<List<RepositoryEntity>> provideRepositoryListObservable() {
        List<RepositoryEntity> repositoryEntityList = new ArrayList<>();
        repositoryEntityList.add(new RepositoryEntity(5301791, "abs-search-view", USER_NAME, "", "1", "1", "1"));
        repositoryEntityList.add(new RepositoryEntity(5528510, "GIS", USER_NAME, "", "1", "1", "1"));
        return just(repositoryEntityList);
    }

    @Test
    public void testRequestRepositoryListUseCase() {

        doAnswer(answer -> provideRepositoryListObservable()).when(githubRepository).getRepositories(anyString());

        TestSubscriber<List<RepositoryEntity>> subscriber = new TestSubscriber<>();
        interactor.execute(anyString()).subscribe(subscriber);

        verify(githubRepository).getRepositories(anyString());

        subscriber.assertNoErrors();
        subscriber.assertValueCount(1);
        subscriber.assertCompleted();

        List<RepositoryEntity> actual = subscriber.getOnNextEvents().get(0);
        Assert.assertNotNull(actual);
        assertEquals(2, actual.size());
        //first
        RepositoryEntity entity = actual.get(0);
        assertEquals(entity.getName(), "abs-search-view");
        assertEquals(entity.getOwnerName(), USER_NAME);
        assertEquals(entity.getId(), 5301791);
        //last
        entity = actual.get(actual.size() - 1);
        assertEquals(entity.getName(), "GIS");
        assertEquals(entity.getOwnerName(), USER_NAME);
        assertEquals(entity.getId(), 5528510);
    }
}
