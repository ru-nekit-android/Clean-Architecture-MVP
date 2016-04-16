package ru.nekit.android.clean_architecture.presentation.core.presenter.persistance;

public interface IComponentCache<C> {
    int generateId();

    C getComponent(int index);

    void setComponent(int index, C component);
}
