package ru.nekit.android.clean_architecture.data.di.network;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.nekit.android.clean_architecture.data.di.api.qualifier.Endpoint;
import ru.nekit.android.clean_architecture.data.di.network.qualifier.OkHttpInterceptors;
import ru.nekit.android.clean_architecture.data.di.network.qualifier.OkHttpNetworkInterceptors;
import ru.nekit.clean_architecture.android.data.BuildConfig;

/**
 * Created by ru.nekit.android on 29.03.16.
 */
@Module
@Singleton
public class NetworkModule {

    @Provides
    @NonNull
    public OkHttpClient provideOkHttpClient(@OkHttpInterceptors List<Interceptor> interceptors,
                                            @OkHttpNetworkInterceptors List<Interceptor> networkInterceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }

        for (Interceptor interceptor : networkInterceptors) {
            builder.addNetworkInterceptor(interceptor);
        }

        return builder.build();
    }

    @Provides
    @NonNull
    public Retrofit provideRetrofit(OkHttpClient client, @Endpoint String endpoint) {
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .validateEagerly(BuildConfig.DEBUG) // Fail early: check Retrofit configuration at creation time in Debug build.
                .build();
    }
}
