package ru.nekit.android.mvpmeeting.utils.rx;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ru.nekit.android on 06.03.16.
 */
public class RxTransformers {

    public static <T> Observable.Transformer<T, T> applySchedulers(Scheduler preScheduler, Scheduler postScheduler) {
        return tObservable -> tObservable.subscribeOn(preScheduler)
                .observeOn(postScheduler);
    }

    public static <T> Observable.Transformer<T, T> applyOperationBeforeAndAfter(Runnable before, Runnable after) {
        return tObservable -> tObservable.doOnSubscribe(before::run).doOnTerminate(after::run);
    }

}
