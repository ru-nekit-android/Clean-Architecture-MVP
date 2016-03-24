package ru.nekit.android.mvpmeeting.model.net;

import com.fernandocejas.frodo.annotation.RxLogObservable;

import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.http.GET;
import retrofit.http.Path;
import ru.nekit.android.mvpmeeting.model.entities.RepositoryEntity;
import rx.Observable;

/**
 * Created by MacOS on 02.03.16.
 */
public interface GitHubApiService {

    @GET("users/{user}/repos")
    @RxLogObservable
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
