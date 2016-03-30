package ru.nekit.android.clean_architecture.domain.interactors.base;

import rx.Observable;

/**
 * Created by ru.nekit.android on 10.03.16.
 */
public interface IInteractor<T, P> {

    Observable<T> execute(P parameter);

}
