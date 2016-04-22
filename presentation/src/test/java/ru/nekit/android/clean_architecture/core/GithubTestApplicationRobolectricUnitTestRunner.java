package ru.nekit.android.clean_architecture.core;

import android.support.annotation.NonNull;

import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.lang.reflect.Method;

import ru.nekit.android.clean_architecture.BuildConfig;
import ru.nekit.android.clean_architecture.TestGithubApplication;
import ru.nekit.android.clean_architecture.di.TestApplicationComponent;

// Custom runner allows us set config in one place instead of setting it in each test class.
public class GithubTestApplicationRobolectricUnitTestRunner extends RobolectricGradleTestRunner {

    // This value should be changed as soon as Robolectric will support newer api.
    private static final int SDK_EMULATE_LEVEL = 21;

    public GithubTestApplicationRobolectricUnitTestRunner(@NonNull Class<?> klass) throws Exception {
        super(klass);
    }

    @NonNull
    public static TestGithubApplication getGithubApplication() {
        return (TestGithubApplication) RuntimeEnvironment.application;
    }

    public static TestApplicationComponent getTestApplicationComponent() {
        return (TestApplicationComponent) getGithubApplication().getApplicationComponent();
    }

    @Override
    public Config getConfig(@NonNull Method method) {
        final Config defaultConfig = super.getConfig(method);
        return new Config.Implementation(
                new int[]{SDK_EMULATE_LEVEL},
                defaultConfig.manifest(),
                defaultConfig.qualifiers(),
                "ru.nekit.android.clean_architecture",
                defaultConfig.resourceDir(),
                defaultConfig.assetDir(),
                defaultConfig.shadows(),
                TestGithubApplication.class, // Notice that we override real application class for Unit tests.
                defaultConfig.libraries(),
                defaultConfig.constants() == Void.class ? BuildConfig.class : defaultConfig.constants()
        );
    }
}
