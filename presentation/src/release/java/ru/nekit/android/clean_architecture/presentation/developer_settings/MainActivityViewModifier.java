package ru.nekit.android.clean_architecture.presentation.developer_settings;

import android.support.annotation.NonNull;
import android.view.View;

import ru.nekit.android.clean_architecture.presentation.view.other.IViewModifier;

/**
 * Created by ru.nekit.android on 30.03.16.
 */
public class MainActivityViewModifier implements IViewModifier {
    @NonNull
    @Override
    public <T extends View> T modify(@NonNull T view) {
        return view;
    }
}
