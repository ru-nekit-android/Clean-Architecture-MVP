package ru.nekit.android.clean_architecture.data.network.di;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import ru.nekit.android.clean_architecture.data.network.di.qualifier.OkHttpInterceptors;
import ru.nekit.android.clean_architecture.data.network.di.qualifier.OkHttpNetworkInterceptors;

import static java.util.Collections.emptyList;

/**
 * Created by ru.nekit.android on 29.03.16.
 */
@Module
@Singleton
public class OkHttpInterceptorsModule {

    @Provides
    @NonNull
    @OkHttpInterceptors
    public List<Interceptor> provideOkHttpInterceptors() {
        return emptyList();
    }

    @Provides
    @NonNull
    @OkHttpNetworkInterceptors
    public List<Interceptor> provideOkHttpNetworkInterceptors() {
        return emptyList();
    }

}
