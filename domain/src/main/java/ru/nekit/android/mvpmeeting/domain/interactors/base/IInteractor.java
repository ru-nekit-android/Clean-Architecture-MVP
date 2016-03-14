package ru.nekit.android.mvpmeeting.domain.interactors.base;

import rx.Observable;

/**
 * Created by MacOS on 10.03.16.
 */
public interface IInteractor<T> {

    Observable<T> execute();

}
