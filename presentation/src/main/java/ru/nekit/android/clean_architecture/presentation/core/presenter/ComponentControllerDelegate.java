package ru.nekit.android.clean_architecture.presentation.core.presenter;

import android.os.Bundle;

public class ComponentControllerDelegate<C> {
    private static final String PRESENTER_INDEX_KEY = "presenter-index";

    private C component;
    private IComponentCache<C> cache;
    private long componentId;
    private boolean isDestroyedBySystem;

    public void onCreate(IComponentCache<C> cache, Bundle savedInstanceState,
                         IComponentFactory<C> componentFactory) {
        this.cache = cache;
        if (savedInstanceState == null) {
            componentId = cache.generateId();
        } else {
            componentId = savedInstanceState.getLong(PRESENTER_INDEX_KEY);
        }
        component = cache.getComponent(componentId);
        if (component == null) {
            component = componentFactory.createComponent();
            cache.setComponent(componentId, component);
        }
    }

    public void onResume() {
        isDestroyedBySystem = false;
    }

    public void onSaveInstanceState(Bundle outState) {
        isDestroyedBySystem = true;
        outState.putLong(PRESENTER_INDEX_KEY, componentId);
    }

    public void onDestroy() {
        if (!isDestroyedBySystem) {
            cache.setComponent(componentId, null);
        }
    }

    public C getComponent() {
        return component;
    }
}
