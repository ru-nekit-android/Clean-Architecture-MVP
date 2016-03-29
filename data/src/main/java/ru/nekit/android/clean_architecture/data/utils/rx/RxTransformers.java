package ru.nekit.android.clean_architecture.data.utils.rx;

import rx.Observable;
import rx.Scheduler;

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
