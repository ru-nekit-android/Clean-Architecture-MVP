package ru.nekit.android.mvpmeeting.model.net;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.nekit.android.mvpmeeting.model.entities.RepositoryEntity;
import rx.Observable;


/**
 * Created by MacOS on 02.03.16.
 */
public interface GitHubApiService {

    @GET("users/{user}/repos")
    Observable<List<RepositoryEntity>> getRepositories(@Path("user") String user);

    class Factory {
        public static GitHubApiService create(String endpoint) {
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(endpoint)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

            return builder.build().create(GitHubApiService.class);
        }
    }

}
