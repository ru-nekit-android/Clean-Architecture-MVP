package ru.nekit.android.mvpmeeting.presentation.internal.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.nekit.android.mvpmeeting.presentation.internal.di.modules.DomainModule;
import ru.nekit.android.mvpmeeting.presentation.internal.di.modules.ModelModule;
import ru.nekit.android.mvpmeeting.presentation.internal.di.modules.PresentationModule;
import ru.nekit.android.mvpmeeting.presentation.view.fragments.RepositoryListFragment;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
@Singleton
@Component(modules = {PresentationModule.class, DomainModule.class, ModelModule.class})
public interface AppComponent {

    void inject(RepositoryListFragment value);
}
