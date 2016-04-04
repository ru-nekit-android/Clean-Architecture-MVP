package ru.nekit.android.clean_architecture.presentation;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import ru.nekit.android.clean_architecture.BuildConfig;
import ru.nekit.android.clean_architecture.presentation.di.ApplicationComponent;
import ru.nekit.android.clean_architecture.presentation.di.DaggerApplicationComponent;
import ru.nekit.android.clean_architecture.presentation.di.modules.ApplicationModule;
import timber.log.Timber;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
public class GithubApp extends Application {

    @NonNull
    private ApplicationComponent applicationComponent;

    @NonNull
    public static GithubApp get(@NonNull Context context) {
        return (GithubApp) context.getApplicationContext();
    }

    @NonNull
    public ApplicationComponent applicationComponent() {
        return applicationComponent;
    }

    public void onCreate() {
        super.onCreate();

        applicationComponent = prepareApplicationComponent().build();
        applicationComponent.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private DaggerApplicationComponent.Builder prepareApplicationComponent() {
        return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this));
    }
}
