package ru.nekit.android.mvpmeeting.presentation;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import ru.nekit.android.mvpmeeting.BuildConfig;
import ru.nekit.android.mvpmeeting.presentation.internal.di.AppComponent;
import ru.nekit.android.mvpmeeting.presentation.internal.di.DaggerAppComponent;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
public class GithubApp extends Application {

    private static AppComponent applicationComponent;

    public void onCreate() {
        super.onCreate();
        initializeInjector();
        initializeLeakDetection();
    }

    private void initializeInjector() {
        applicationComponent = DaggerAppComponent.create();
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    public static AppComponent getApplicationComponent() {
        return applicationComponent;
    }

}
