package ru.nekit.android.clean_architecture.data.network;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import ru.nekit.android.clean_architecture.data.network.qualifier.OkHttpInterceptors;
import ru.nekit.android.clean_architecture.data.network.qualifier.OkHttpNetworkInterceptors;

import static java.util.Collections.emptyList;

/**
 * Created by ru.nekit.android on 29.03.16.
 */
@Module
public class OkHttpInterceptorsModule {

    @Singleton
    @Provides
    @NonNull
    @OkHttpInterceptors
    public List<Interceptor> provideOkHttpInterceptors() {
        return emptyList();
    }

    @Singleton
    @Provides
    @NonNull
    @OkHttpNetworkInterceptors
    public List<Interceptor> provideOkHttpNetworkInterceptors() {
        return emptyList();
    }

}
