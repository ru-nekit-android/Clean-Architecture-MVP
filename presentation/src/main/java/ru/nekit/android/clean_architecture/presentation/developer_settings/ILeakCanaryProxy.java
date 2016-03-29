package ru.nekit.android.clean_architecture.presentation.developer_settings;

import android.support.annotation.NonNull;

/**
 * Created by ru.nekit.android on 29.03.16.
 */
public interface ILeakCanaryProxy {

    void init();

    void watch(@NonNull Object object);
}
