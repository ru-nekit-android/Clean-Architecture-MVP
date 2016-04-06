package ru.nekit.android.clean_architecture.presentation.di;

import dagger.Component;
import ru.nekit.android.clean_architecture.presentation.core.view.activity.MVPBaseActivity;
import ru.nekit.android.clean_architecture.presentation.di.modules.ActivityModule;
import ru.nekit.android.clean_architecture.presentation.di.scope.PerActivity;

@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {

    MVPBaseActivity activity();
}
