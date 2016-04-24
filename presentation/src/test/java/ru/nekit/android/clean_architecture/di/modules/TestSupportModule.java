package ru.nekit.android.clean_architecture.di.modules;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import ru.nekit.android.clean_architecture.core.TestUtils;
import ru.nekit.android.clean_architecture.data.di.api.qualifier.Endpoint;
import ru.nekit.android.clean_architecture.data.entities.RepositoryDTO;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.UserName;
import rx.Observable;

/**
 * Created by ru.nekit.android on 20.04.16.
 */
@Module
@Singleton
public class TestSupportModule {

    @Provides
    @NonNull
    public TestUtils providesTestUtils() {
        return new TestUtils();
    }

    @Provides
    @NonNull
    public MockWebServer providesMockWebServer(@Endpoint String endpoint, @UserName String userName, TestUtils testUtils) {
        MockWebServer server = new MockWebServer();
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
        return server;
    }

    @Provides
    public Observable<List<RepositoryDTO>> provideRepositoryListObservable(TestUtils testUtils) {
        List<RepositoryDTO> repositoryDTOs = Arrays.asList(testUtils.readJson("json/repos", RepositoryDTO[].class));
        return Observable.just(repositoryDTOs);
    }
}
