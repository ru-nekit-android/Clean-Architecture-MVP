package ru.nekit.android.clean_architecture.data.network;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import ru.nekit.android.clean_architecture.data.network.qualifier.OkHttpInterceptors;
import ru.nekit.android.clean_architecture.data.network.qualifier.OkHttpNetworkInterceptors;

/**
 * Created by ru.nekit.android on 29.03.16.
 */
@Module
public class NetworkModule {

    @NonNull
    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient(@OkHttpInterceptors List<Interceptor> interceptors, @OkHttpNetworkInterceptors List<Interceptor> networkInterceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }

        for (Interceptor interceptor : networkInterceptors) {
            builder.addNetworkInterceptor(interceptor);
        }

        return builder.build();
    }

}
