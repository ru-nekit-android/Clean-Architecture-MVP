package ru.nekit.android.clean_architecture.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import ru.nekit.android.clean_architecture.BaseTest;
import ru.nekit.android.clean_architecture.data.di.api.GithubModule;
import ru.nekit.android.clean_architecture.data.di.network.NetworkModule;
import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by ru.nekit.android on 20.04.16.
 */
public class GithubApiTest extends BaseTest {

    public static final String TEST_USER_NAME = "ru-nekit-android";
    public static final String BASE_URL = "https://api.github.com/";
    @Inject
    protected GithubModule.Api githubApi;
    private MockWebServer server;

    @Before
    public void setUp() throws IOException {

        super.setUp();

        testApplicationComponent.inject(this);

        server = new MockWebServer();
        server.start();

        final Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                MockResponse response = new MockResponse();

                if (request.getPath().equals("/users/" + TEST_USER_NAME + "/repos")) {
                    return response.setResponseCode(200)
                            .setBody(testUtils.readString("json/repos"));
                }
                return response.setResponseCode(404);
            }
        };

        server.setDispatcher(dispatcher);
        HttpUrl baseUrl = server.url(BASE_URL);

        NetworkModule networkModule = new NetworkModule();

    }

    @Test
    public void testApiIsNotNull() {
        assertNotNull(githubApi);
    }

    @Test
    public void testGetRepositoryList() {
        TestSubscriber<List<RepositoryEntity>> testSubscriber = new TestSubscriber<>();
        githubApi.getRepositories(TEST_USER_NAME).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        List<RepositoryEntity> actual = testSubscriber.getOnNextEvents().get(0);
        assertEquals(30, actual.size());
        RepositoryEntity entity = actual.get(0);
        assertEquals(entity.getName(), "abs-search-view");
        assertTrue(entity.getId() == 5301791);
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

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}
