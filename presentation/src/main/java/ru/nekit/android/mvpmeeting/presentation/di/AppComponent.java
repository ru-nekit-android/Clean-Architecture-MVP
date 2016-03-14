package ru.nekit.android.mvpmeeting.presentation.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.nekit.android.mvpmeeting.presentation.view.fragments.RepositoryListFragment;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
@Singleton
@Component(modules = {DomainModule.class})
public interface AppComponent {

    void inject(RepositoryListFragment value);
}
