package ru.nekit.android.clean_architecture.domain.core.mapper;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
public abstract class BaseMapper<T, R> implements Func1<List<T>, List<R>> {
    @Override
    public List<R> call(List<T> inList) {
        return Observable.from(inList)
                .map(this::convert)
                .toList()
                .toBlocking()
                .first();
    }

    protected abstract R convert(T value);

}
