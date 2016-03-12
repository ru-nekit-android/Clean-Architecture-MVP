package ru.nekit.android.mvpmeeting.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.mvpmeeting.Const;
import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.api.ApiModule;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
public class DomainModule {

    @Provides
    @Singleton
    ApiInterface provideApiInterface() {
        return ApiModule.getApiInterface();
    }

    @Provides
    @Singleton
    @Named(Const.PRE_THREAD)
    Scheduler providePreScheduler() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named(Const.POST_THREAD)
    Scheduler providePostScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
