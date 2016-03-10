package ru.nekit.android.mvpmeeting.model.interactors.base;

import rx.Observable;

/**
 * Created by MacOS on 10.03.16.
 */
public interface IInteractor<T> {

    Observable<T> execute();

}
