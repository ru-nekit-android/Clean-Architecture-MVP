package ru.nekit.android.mvpmeeting.presentation.internal.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
public class PresentationModule {

    @Singleton
    @Provides
    public CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
