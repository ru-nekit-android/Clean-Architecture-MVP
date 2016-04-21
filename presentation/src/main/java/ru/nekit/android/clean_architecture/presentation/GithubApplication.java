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
public class GithubApplication extends Application {

    @NonNull
    private ApplicationComponent applicationComponent;

    @NonNull
    public static GithubApplication get(@NonNull Context context) {
        return (GithubApplication) context.getApplicationContext();
    }

    @NonNull
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void onCreate() {
        super.onCreate();

        applicationComponent = prepareApplicationComponent();
        applicationComponent.inject(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    protected ApplicationComponent prepareApplicationComponent() {
        return DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }
}
