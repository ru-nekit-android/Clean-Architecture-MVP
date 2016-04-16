package ru.nekit.android.clean_architecture.presentation.core.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import java.util.concurrent.atomic.AtomicInteger;

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
            int seed;
            if (savedInstanceState == null) {
                seed = 0;
            } else {
                seed = savedInstanceState.getInt(NEXT_ID_KEY);
            }
            nonConfigurationInstance = new NonConfigurationInstance(seed);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NEXT_ID_KEY, nonConfigurationInstance.nextId.get());
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return nonConfigurationInstance;
    }

    @Override
    public int generateId() {
        return nonConfigurationInstance.nextId.getAndIncrement();
    }

    @SuppressWarnings("unchecked")
    @Override
    public final C getComponent(int index) {
        return (C) nonConfigurationInstance.components.get(index);
    }

    @Override
    public void setComponent(int index, Object component) {
        nonConfigurationInstance.components.put(index, component);
    }

    private static class NonConfigurationInstance {
        private final SparseArray<Object> components;
        private final AtomicInteger nextId;

        public NonConfigurationInstance(int seed) {
            components = new SparseArray<>();
            nextId = new AtomicInteger(seed);
        }
    }
}
