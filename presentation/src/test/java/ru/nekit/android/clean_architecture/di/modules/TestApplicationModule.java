package ru.nekit.android.clean_architecture.di.modules;

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
import rx.schedulers.Schedulers;

/**
 * Created by ru.nekit.android on 22.04.16.
 */
@Module
@Singleton
public class TestApplicationModule {

    @NonNull
    private final Application application;

    public TestApplicationModule(@NonNull Application application) {
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
        return paperwork.get("USER_NAME");
    }

    @Provides
    @Endpoint
    @NonNull
    public String provideEndpoint(Paperwork paperwork) {
        return paperwork.get("API_URL");
    }

    @Provides
    @LongOperationThread
    @NonNull
    public Scheduler provideLongOperationThread() {
        return Schedulers.immediate();
    }

    @Provides
    @MainThread
    @NonNull
    public Scheduler provideUIThread() {
        return Schedulers.immediate();
    }
}
