package ru.nekit.android.mvpmeeting.presentation.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.nekit.android.mvpmeeting.domain.interactors.ObtainRepositoriesInteractor;
import ru.nekit.android.mvpmeeting.model.repositiory.IRepository;

/**
 * Created by ru.nekit.android on 08.03.16.
 */

@Module
public class DomainModule {

    @Provides
    @Singleton
    ObtainRepositoriesInteractor provideObtain(IRepository repository) {
        return new ObtainRepositoriesInteractor(repository);
    }

}
