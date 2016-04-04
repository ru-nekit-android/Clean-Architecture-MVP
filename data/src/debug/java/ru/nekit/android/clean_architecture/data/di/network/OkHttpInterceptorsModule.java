package ru.nekit.android.clean_architecture.data.di.network;

import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import ru.nekit.android.clean_architecture.data.di.network.qualifier.OkHttpInterceptors;
import ru.nekit.android.clean_architecture.data.di.network.qualifier.OkHttpNetworkInterceptors;
import timber.log.Timber;

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
        return Collections.singletonList(new HttpLoggingInterceptor(Timber::d));
    }

    @Provides
    @OkHttpNetworkInterceptors
    @NonNull
    public List<Interceptor> provideOkHttpNetworkInterceptors() {
        return Collections.singletonList(new StethoInterceptor());
    }

}
