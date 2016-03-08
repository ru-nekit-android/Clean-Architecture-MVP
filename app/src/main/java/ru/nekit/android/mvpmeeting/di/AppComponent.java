package ru.nekit.android.mvpmeeting.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.nekit.android.mvpmeeting.model.GithubModel;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
@Singleton
@Component(modules = {ModelModule.class})
public interface AppComponent {
    void inject(GithubModel model);
}
