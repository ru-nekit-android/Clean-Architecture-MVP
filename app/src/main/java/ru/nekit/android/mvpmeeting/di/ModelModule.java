package ru.nekit.android.mvpmeeting.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.mvpmeeting.model.api.ApiInterface;
import ru.nekit.android.mvpmeeting.model.api.ApiModule;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
public class ModelModule {

    @Provides
    @Singleton
    ApiInterface provideApiInterface() {
        return ApiModule.getApiInterface();
    }
}
