package ru.nekit.android.clean_architecture.domain.core.interactors;

import rx.Observable;

/**
 * Created by ru.nekit.android on 10.03.16.
 */
public interface IInteractor<R, P> {

    Observable<R> execute(P parameter);

}
