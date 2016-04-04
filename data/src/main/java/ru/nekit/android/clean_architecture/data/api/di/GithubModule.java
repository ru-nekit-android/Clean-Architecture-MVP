package ru.nekit.android.clean_architecture.data.api.di;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.nekit.android.clean_architecture.data.entities.RepositoryEntity;
import ru.nekit.android.clean_architecture.data.network.di.NetworkModule;
import rx.Observable;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
@Module(includes = {NetworkModule.class})
@Singleton
public class GithubModule {

    @Provides
    @NonNull
    public Api provideApi(@NonNull Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

    public interface Api {

        @GET("users/{user}/repos")
        Observable<List<RepositoryEntity>> getRepositories(@Path("user") String user);

    }
}
