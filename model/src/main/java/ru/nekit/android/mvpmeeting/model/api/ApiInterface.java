package ru.nekit.android.mvpmeeting.model.api;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by MacOS on 02.03.16.
 */
public interface ApiInterface {

    @GET("users/{user}/repos")
    Observable<List<ru.nekit.android.mvpmeeting.model.RepositoryDTO>> getRepositories(@Path("user") String user);

}
