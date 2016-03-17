package ru.nekit.android.mvpmeeting.model.net;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import ru.nekit.android.mvpmeeting.model.entities.RepositoryEntity;
import rx.Observable;

/**
 * Created by MacOS on 02.03.16.
 */
public interface GitHubApiService {

    @GET("users/{user}/repos")
    Observable<List<RepositoryEntity>> getRepositories(@Path("user") String user);

}
