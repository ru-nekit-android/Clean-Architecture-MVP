package ru.nekit.android.clean_architecture.data.api;

import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import ru.nekit.android.clean_architecture.data.BaseTest;
import ru.nekit.android.clean_architecture.data.di.api.GithubModule;
import ru.nekit.android.clean_architecture.data.di.network.NetworkModule;
import ru.nekit.android.clean_architecture.data.entities.RepositoryDTO;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by ru.nekit.android on 20.04.16.
 */
public class GithubApiTest extends BaseTest {

    private final String userName = "ru-nekit-android";

    private final String endpoint = "https://api.github.com/";

    private MockWebServer server;

    private GithubApi githubApi;

    @Override
    public void setUp() {
        server = new MockWebServer();
        try {
            server.start();
        } catch (IOException error) {
            //empty
        }
        final Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                MockResponse response = new MockResponse();

                if (request.getPath().equals("/users/" + userName + "/repos")) {
                    return response.setResponseCode(200)
                            .setBody(testUtils.readString("json/repos"));
                }
                return response.setResponseCode(404);
            }
        };
        server.setDispatcher(dispatcher);
        server.url(endpoint);

        NetworkModule networkModule = new NetworkModule();
        githubApi = new GithubModule().provideApi(networkModule.provideRetrofit(networkModule.provideOkHttpClient(Collections.<Interceptor>emptyList(), Collections.<Interceptor>emptyList()), endpoint));
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
        TestSubscriber<List<RepositoryDTO>> testSubscriber = new TestSubscriber<>();
        githubApi.getRepositories(userName).subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        List<RepositoryDTO> actual = testSubscriber.getOnNextEvents().get(0);
        assertEquals(30, actual.size());
        //first
        RepositoryDTO entity = actual.get(0);
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
    public void tearDown() {
        try {
            server.shutdown();
        } catch (IOException error) {
            //empty
        }
    }
}
