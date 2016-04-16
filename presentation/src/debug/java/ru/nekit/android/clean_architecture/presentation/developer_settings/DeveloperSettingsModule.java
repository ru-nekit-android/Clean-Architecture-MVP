package ru.nekit.android.clean_architecture.presentation.developer_settings;

import android.app.Application;
import android.support.annotation.NonNull;

import com.github.pedrovgs.lynx.LynxConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.supercluster.paperwork.Paperwork;
import ru.nekit.android.clean_architecture.presentation.model.DeveloperSettingsViewModel;
import ru.nekit.android.clean_architecture.presentation.model.IDeveloperSettingsViewModel;
import ru.nekit.android.clean_architecture.presentation.view.other.IViewModifier;

/**
 * Created by ru.nekit.android on 29.03.16.
 */
@Module
@Singleton
public class DeveloperSettingsModule {

    @Provides
    @NonNull
    public ILeakCanaryProxy provideILeakCanaryProxy(@NonNull Application application) {
        return new LeakCanaryProxy(application);
    }

    @Provides
    @NonNull
    public LynxConfig provideLynxConfig() {
        return new LynxConfig();
    }

    @Provides
    @NonNull
    public IViewModifier provideIViewModifier() {
        return new MainActivityViewModifier();
    }

    @Provides
    @NonNull
    public IDeveloperSettingsViewModel provideIDeveloperSettingsModel(@NonNull Paperwork paperwork) {
        return new DeveloperSettingsViewModel(paperwork);
    }
}
