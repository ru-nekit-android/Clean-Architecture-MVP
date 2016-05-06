package ru.nekit.android.clean_architecture.presentation.tools;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.runner.AndroidJUnitRunner;

import ru.nekit.android.clean_architecture.presentation.GithubApplication;

/**
 * Created by ru.nekit.android on 06.05.16.
 */
public class InstrumentationTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(@NonNull ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, GithubApplication.class.getName(), context);
    }

}
