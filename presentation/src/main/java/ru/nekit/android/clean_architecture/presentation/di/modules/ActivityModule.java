package ru.nekit.android.clean_architecture.presentation.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.github.pedrovgs.lynx.LynxActivity;
import com.github.pedrovgs.lynx.LynxConfig;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.clean_architecture.presentation.core.view.activity.MVPBaseActivity;
import ru.nekit.android.clean_architecture.presentation.di.scope.PerActivity;
import ru.nekit.android.clean_architecture.presentation.navigation.NavigationToLogcatCommand;
import ru.nekit.android.clean_architecture.presentation.navigation.qualifier.NavigateToLogcat;

@Module
@PerActivity
public class ActivityModule {
    private final MVPBaseActivity activity;

    public ActivityModule(MVPBaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    MVPBaseActivity activity() {
        return this.activity;
    }

}
