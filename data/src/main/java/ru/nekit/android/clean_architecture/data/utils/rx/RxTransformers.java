package ru.nekit.android.clean_architecture.data.utils.rx;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by ru.nekit.android on 06.03.16.
 */
public final class RxTransformers {

    private RxTransformers() {
        //not called
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> applySchedulers(@NonNull Scheduler preScheduler, @NonNull Scheduler postScheduler) {
        return tObservable -> tObservable.subscribeOn(preScheduler)
                .observeOn(postScheduler);
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> applyOperationBeforeAndAfter(@NonNull Runnable before, @NonNull Runnable after) {
        return tObservable -> tObservable.doOnSubscribe(before::run).doOnTerminate(after::run);
    }

}
