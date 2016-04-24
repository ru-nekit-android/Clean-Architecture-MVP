package ru.nekit.android.clean_architecture.domain;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import ru.nekit.android.clean_architecture.core.BaseTest;
import ru.nekit.android.clean_architecture.data.entities.RepositoryDTO;
import ru.nekit.android.clean_architecture.data.mapper.RepositoryDTOToEntityMapper;
import ru.nekit.android.clean_architecture.di.TestRepositoryListComponent;
import ru.nekit.android.clean_architecture.di.modules.TestRepositoryListModule;
import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;
import ru.nekit.android.clean_architecture.domain.interactors.RequestRepositoryListUseCase;
import ru.nekit.android.clean_architecture.domain.repository.IGithubRepository;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.UserName;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by ru.nekit.android on 22.04.16.
 */
public class RequestRepositoryListUseCaseTest extends BaseTest {

    @Inject
    protected IGithubRepository githubRepository;
    @Inject
    @UserName
    protected String userName;
    @Inject
    protected Observable<List<RepositoryDTO>> repositoryDTOObservable;
    //real
    private RequestRepositoryListUseCase useCase;

    @Override
    public void setUp() throws IOException {
        super.setUp();
        TestRepositoryListComponent component = testApplicationComponent.plus(new TestRepositoryListModule());
        component.inject(this);
        useCase = new RequestRepositoryListUseCase(githubRepository);
    }

    @Test
    public void testRequestRepositoryListUseCase() {
        when(githubRepository.getRepositories(userName)).thenReturn(repositoryDTOObservable.map(new RepositoryDTOToEntityMapper()));

        TestSubscriber<List<RepositoryEntity>> subscriber = new TestSubscriber<>();
        useCase.execute(userName).subscribe(subscriber);

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
