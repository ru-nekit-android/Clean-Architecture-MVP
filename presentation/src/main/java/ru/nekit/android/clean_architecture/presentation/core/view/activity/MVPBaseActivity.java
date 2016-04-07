package ru.nekit.android.clean_architecture.presentation.core.view.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

import ru.nekit.android.clean_architecture.presentation.GithubApplication;
import ru.nekit.android.clean_architecture.presentation.di.ApplicationComponent;

/**
 * Created by ru.nekit.android on 06.04.16.
 */
public class MVPBaseActivity extends AppCompatActivity {


    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ApplicationComponent applicationComponent() {
        return GithubApplication.get(this).applicationComponent();
    }
}
