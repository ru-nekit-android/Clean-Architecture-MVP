package ru.nekit.android.clean_architecture.presentation.core.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import ru.nekit.android.clean_architecture.presentation.core.presenter.persistance.IComponentCache;

/**
 * Created by ru.nekit.android on 06.04.16.
 */
public class MVPActivity<C> extends AppCompatActivity implements IComponentCache<C> {

    private static final String NEXT_ID_KEY = "next-presenter-id";
    private NonConfigurationInstance nonConfigurationInstance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        nonConfigurationInstance = (NonConfigurationInstance) getLastCustomNonConfigurationInstance();
        if (nonConfigurationInstance == null) {
            long seed;
            if (savedInstanceState == null) {
                seed = 0;
            } else {
                seed = savedInstanceState.getLong(NEXT_ID_KEY);
            }
            nonConfigurationInstance = new NonConfigurationInstance(seed);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(NEXT_ID_KEY, nonConfigurationInstance.nextId.get());
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return nonConfigurationInstance;
    }

    @Override
    public long generateId() {
        return nonConfigurationInstance.nextId.getAndIncrement();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final C getComponent(long index) {
        return (C) nonConfigurationInstance.components.get(index);
    }

    @Override
    public void setComponent(long index, Object component) {
        nonConfigurationInstance.components.put(index, component);
    }

    private static class NonConfigurationInstance {
        private final Map<Long, Object> components;
        private final AtomicLong nextId;

        public NonConfigurationInstance(long seed) {
            components = new HashMap<>();
            nextId = new AtomicLong(seed);
        }
    }
}
