package ru.nekit.android.mvpmeeting.data.api;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import ru.nekit.android.mvpmeeting.data.RepositoryDTO;
import rx.Observable;

/**
 * Created by MacOS on 02.03.16.
 */
public interface ApiInterface {

    @GET("users/{user}/repos")
    Observable<List<RepositoryDTO>> getRepositories(@Path("user") String user);

}
