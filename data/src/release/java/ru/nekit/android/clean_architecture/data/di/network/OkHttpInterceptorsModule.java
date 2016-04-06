package ru.nekit.android.clean_architecture.data.di.network;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import ru.nekit.android.clean_architecture.data.di.network.qualifier.OkHttpInterceptors;
import ru.nekit.android.clean_architecture.data.di.network.qualifier.OkHttpNetworkInterceptors;

import static java.util.Collections.emptyList;

/**
 * Created by ru.nekit.android on 29.03.16.
 */
@Module
@Singleton
public class OkHttpInterceptorsModule {

    @Provides
    @OkHttpInterceptors
    @NonNull
    public List<Interceptor> provideOkHttpInterceptors() {
        return emptyList();
    }

    @Provides
    @OkHttpNetworkInterceptors
    @NonNull
    public List<Interceptor> provideOkHttpNetworkInterceptors() {
        return emptyList();
    }

}
