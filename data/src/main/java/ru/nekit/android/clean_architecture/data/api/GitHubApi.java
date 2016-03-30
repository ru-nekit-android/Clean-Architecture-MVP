package ru.nekit.android.clean_architecture.data.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.nekit.android.clean_architecture.data.entities.RepositoryEntity;
import rx.Observable;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public interface GitHubApi {

    @GET("users/{user}/repos")
    Observable<List<RepositoryEntity>> getRepositories(@Path("user") String user);

}
