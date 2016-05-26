package ru.nekit.android.clean_architecture.presentation.core.presenter;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import ru.nekit.android.clean_architecture.presentation.core.model.IMVPViewModel;
import ru.nekit.android.clean_architecture.presentation.core.view.IMVPView;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
public class MVPPresenter<V extends IMVPView, VM extends IMVPViewModel> implements IMVPPresenter<V, VM> {

    @Nullable
    private VM mViewModel;
    private CompositeSubscription mSubscriptionList;
    private BehaviorSubject<WeakReference<V>> mViewSubject;
    private BehaviorSubject<VM> mViewModelSubject;
    private WeakReference<V> mViewRef;

    protected MVPPresenter(@Nullable VM viewModel) {
        this.mViewModel = viewModel;
        mViewModelSubject = BehaviorSubject.create(viewModel);
        mSubscriptionList = new CompositeSubscription();
    }

    protected final void addSubscriber(@NonNull Subscription subscription) {
        mSubscriptionList.add(subscription);
    }

    @CallSuper
    public void onDestroy() {
        if (mSubscriptionList != null) {
            mSubscriptionList.clear();
        }
        mSubscriptionList = null;
        mViewModelSubject.onNext(null);
        mViewModelSubject = null;
        mViewModel = null;

    }

    @Nullable
    public VM getViewModel() {
        return mViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onSaveInstanceState(@Nullable Bundle bundle) {

    }

    @Override
    public void onAttachView(@NonNull V view) {

    }

    @Override
    public void attachView(@NonNull V view) {
        mViewRef = new WeakReference<>(view);
        mViewSubject = BehaviorSubject.create(mViewRef);
    }

    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewSubject.onNext(null);
        }
        mViewRef = null;
        mViewSubject = null;
    }

    @Override
    @Nullable
    public V getView() {
        if (mViewRef != null && mViewRef.get() != null) {
            return mViewRef.get();
        }
        return null;
    }

    @NonNull
    private Observable<V> getViewObservable() {
        return mViewSubject.
                filter(views -> views != null && views.get() != null).
                map(Reference::get);
    }

    @NonNull
    private Observable<VM> getViewModelObservable() {
        return mViewModelSubject.filter(model -> model != null);
    }

    public void withView(@NonNull Action1<V> action) {
        getViewObservable().subscribe(action).unsubscribe();
    }

    public void withViewModel(@NonNull Action1<VM> action) {
        getViewModelObservable().subscribe(action).unsubscribe();
    }

    public void withViewAndModel(@NonNull Action2<V, VM> action) {
        Observable.zip(getViewObservable(), getViewModelObservable(), (v, vm) ->
                {
                    action.call(v, vm);
                    return action;
                }
        ).subscribe().unsubscribe();
    }
}
