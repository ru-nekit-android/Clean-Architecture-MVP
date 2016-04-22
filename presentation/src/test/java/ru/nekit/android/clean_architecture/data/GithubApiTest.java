package ru.nekit.android.clean_architecture.data;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import okhttp3.mockwebserver.MockWebServer;
import ru.nekit.android.clean_architecture.core.BaseTest;
import ru.nekit.android.clean_architecture.data.api.GithubApi;
import ru.nekit.android.clean_architecture.data.di.api.GithubModule;
import ru.nekit.android.clean_architecture.data.di.api.qualifier.Endpoint;
import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.UserName;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by ru.nekit.android on 20.04.16.
 */
public class GithubApiTest extends BaseTest {

    @Inject
    @UserName
    protected String userName;

    @Inject
    @Endpoint
    protected String endpoint;

    @Inject
    protected MockWebServer server;

    @Inject
    protected GithubApi githubApi;

    @Before
    public void setUp() throws IOException {
        super.setUp();
        testApplicationComponent.inject(this);
    }

    @Test
    public void testApiIsNotNull() {
        assertNotNull(githubApi);
    }

    @Test
    public void testUserNameIsNotNullAndIsNotEmpty() {
        assertNotNull(userName);
        assertEquals(userName.isEmpty(), false);
    }

    @Test
    public void testGetRepositoryList() {
        TestSubscriber<List<RepositoryEntity>> testSubscriber = new TestSubscriber<>();
        githubApi.getRepositories(userName).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        List<RepositoryEntity> actual = testSubscriber.getOnNextEvents().get(0);
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

    @Test
    public void testGetRepositoryListIncorrect() throws Exception {
        try {
            githubApi.getRepositories("IncorrectRequest").subscribe();
        } catch (Exception expected) {
            assertEquals("HTTP 404 Not Found", expected.getMessage());
        }
    }

    @Override
    public void tearDown() throws Exception {
        server.shutdown();
    }
}
