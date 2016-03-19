package ru.nekit.android.mvpmeeting.presentation;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import ru.nekit.android.mvpmeeting.BuildConfig;
import ru.nekit.android.mvpmeeting.presentation.internal.di.AppComponent;
import ru.nekit.android.mvpmeeting.presentation.internal.di.DaggerAppComponent;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
public class GithubApp extends Application {

    private static AppComponent applicationComponent;
    private static GithubApp instance;
    private RefWatcher refWatcher;

    public static AppComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static RefWatcher getRefWatcher() {
        return instance.refWatcher;
    }

    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initializeLeakDetection();
        instance = (GithubApp) getApplicationContext();
    }

    private void initializeInjector() {
        applicationComponent = DaggerAppComponent.create();
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            refWatcher = LeakCanary.install(this);
        }
    }

}
