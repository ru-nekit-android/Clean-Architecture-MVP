package ru.nekit.android.clean_architecture.presentation.navigation;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.nekit.android.clean_architecture.presentation.navigation.NavigationCommand;

/**
 * Created by ru.nekit.android on 30.03.16.
 */
public abstract class NavigationToLogcatCommand implements NavigationCommand {

    private Context context;

    public void setParentActivity(@NonNull Context context) {
        this.context = context;
    }

    @NonNull
    public Context getParentActivity() {
        return context;
    }
}
