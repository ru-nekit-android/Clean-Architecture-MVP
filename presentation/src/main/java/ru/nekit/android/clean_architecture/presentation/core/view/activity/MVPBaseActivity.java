package ru.nekit.android.clean_architecture.presentation.core.view.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

import ru.nekit.android.clean_architecture.presentation.GithubApplication;
import ru.nekit.android.clean_architecture.presentation.di.ApplicationComponent;
import ru.nekit.android.clean_architecture.presentation.di.modules.ActivityModule;

/**
 * Created by ru.nekit.android on 06.04.16.
 */
public class MVPBaseActivity extends AppCompatActivity {

    private ActivityModule activityModule;

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityModule = new ActivityModule(this);
    }

    public ApplicationComponent applicationComponent() {
        return GithubApplication.get(this).applicationComponent();
    }

    public ActivityModule activityModule() {
        return activityModule;
    }

}
