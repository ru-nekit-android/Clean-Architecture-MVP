package ru.nekit.android.clean_architecture.presentation.developer_settings;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ru.nekit.android on 29.03.16.
 */
@Module
public class DeveloperSettingsModule {

    @Singleton
    @NonNull
    @Provides
    public ILeakCanaryProxy provideILeakCanaryProxy() {
        return new LeakCanaryProxy();
    }
}
