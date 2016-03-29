package ru.nekit.android.clean_architecture.presentation.developer_settings;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by ru.nekit.android on 29.03.16.
 */
public class LeakCanaryProxy implements ILeakCanaryProxy {

    @NonNull
    private final Application application;
    @Nullable
    private RefWatcher watcher;

    public LeakCanaryProxy(@NonNull Application application) {
        this.application = application;
    }

    @Override
    public void init() {
        watcher = LeakCanary.install(application);
    }

    @Override
    public void watch(@NonNull Object object) {
        if (watcher != null) {
            watcher.watch(object);
        }
    }
}
