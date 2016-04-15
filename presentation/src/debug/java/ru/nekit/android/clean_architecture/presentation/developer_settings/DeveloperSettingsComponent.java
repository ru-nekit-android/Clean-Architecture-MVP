package ru.nekit.android.clean_architecture.presentation.developer_settings;

import dagger.Subcomponent;
import ru.nekit.android.clean_architecture.presentation.core.presenter.IHasPresenter;
import ru.nekit.android.clean_architecture.presentation.presenter.DeveloperSettingsPresenter;

/**
 * Created by ru.nekit.android on 30.03.16.
 */
@Subcomponent
public interface DeveloperSettingsComponent extends IHasPresenter<DeveloperSettingsPresenter> {

}
