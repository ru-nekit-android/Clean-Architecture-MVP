package ru.nekit.android.clean_architecture.presentation.di.modules;

import android.app.Application;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.supercluster.paperwork.Paperwork;
import ru.nekit.android.clean_architecture.data.di.api.qualifier.Endpoint;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.LongOperationThread;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.MainThread;
import ru.nekit.android.clean_architecture.presentation.di.qualifier.UserName;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ru.nekit.android on 29.03.16.
 */
@Module
@Singleton
public class ApplicationModule {

    @NonNull
    private final Application application;

    public ApplicationModule(@NonNull Application application) {
        this.application = application;
    }

    @Provides
    @NonNull
    public Application provideApplication() {
        return application;
    }

    @Provides
    @NonNull
    public Paperwork providePaperwork(@NonNull Application application) {
        return new Paperwork(application);
    }

    @Provides
    @UserName
    @NonNull
    public String provideUserName(Paperwork paperwork) {
        return paperwork.get("userName");
    }

    @Provides
    @Endpoint
    @NonNull
    public String provideEndpoint(Paperwork paperwork) {
        return paperwork.get("apiUrl");
    }

    @Provides
    @LongOperationThread
    @NonNull
    public Scheduler provideLongOperationThread() {
        return Schedulers.io();
    }

    @Provides
    @MainThread
    @NonNull
    public Scheduler provideUIThread() {
        return AndroidSchedulers.mainThread();
    }
}
