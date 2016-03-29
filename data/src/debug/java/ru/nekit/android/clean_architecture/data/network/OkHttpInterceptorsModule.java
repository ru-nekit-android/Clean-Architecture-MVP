package ru.nekit.android.clean_architecture.data.network;

import android.support.annotation.NonNull;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;
import ru.nekit.android.clean_architecture.data.network.qualifier.OkHttpNetworkInterceptors;
import timber.log.Timber;

import static java.util.Collections.singletonList;

/**
 * Created by ru.nekit.android on 29.03.16.
 */
@Module
public class OkHttpInterceptorsModule {

    @Singleton
    @Provides
    @NonNull
    @OkHttpNetworkInterceptors
    public List<Interceptor> provideOkHttpInterceptors() {
        return singletonList(new HttpLoggingInterceptor(message -> {
            Timber.d(message);
        }));
    }

    @Singleton
    @Provides
    @NonNull
    @OkHttpNetworkInterceptors
    public List<Interceptor> provideOkHttpNetworkInterceptors() {
        return singletonList(new StethoInterceptor());
    }

}
