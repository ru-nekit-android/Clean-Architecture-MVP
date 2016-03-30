package ru.nekit.android.clean_architecture.presentation.developer_settings;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import ru.nekit.android.clean_architecture.R;
import ru.nekit.android.clean_architecture.presentation.view.other.IViewModifier;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by ru.nekit.android on 30.03.16.
 */
public class MainActivityViewModifier implements IViewModifier {
    @NonNull
    @Override
    public <T extends View> T modify(@NonNull T view) {
        // Basically, what we do here is adding a Developer Setting Fragment to a DrawerLayout!
        DrawerLayout drawerLayout = (DrawerLayout) view.findViewById(R.id.main_drawer_layout);

        DrawerLayout.LayoutParams layoutParams = new DrawerLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT);
        layoutParams.gravity = Gravity.END;

        drawerLayout.addView(LayoutInflater.from(view.getContext()).inflate(R.layout.developer_settings_view, drawerLayout, false), layoutParams);
        return view;
    }
}
