package ru.nekit.android.clean_architecture.presentation.core.presenter.persistance;

public interface IComponentCache<C> {
    long generateId();

    C getComponent(long index);

    void setComponent(long index, C component);
}
