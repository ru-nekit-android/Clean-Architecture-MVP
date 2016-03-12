package ru.nekit.android.mvpmeeting.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.nekit.android.mvpmeeting.GithubApp;
import ru.nekit.android.mvpmeeting.view.fragments.RepositoryListFragment;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
@Singleton
@Component(modules = {DomainModule.class})
public interface AppComponent {

    void inject(RepositoryListFragment value);
}
