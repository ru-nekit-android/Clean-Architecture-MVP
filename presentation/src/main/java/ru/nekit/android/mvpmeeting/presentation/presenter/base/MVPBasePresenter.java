package ru.nekit.android.mvpmeeting.presentation.presenter.base;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import ru.nekit.android.mvpmeeting.presentation.model.base.IViewModel;
import ru.nekit.android.mvpmeeting.presentation.view.base.IMVPView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
public abstract class MVPBasePresenter<V extends IMVPView, VM extends IViewModel> implements IMVPPresenter<V, VM> {

    protected VM viewModel;
    private CompositeSubscription subscriptionList;
    private WeakReference<V> mViewRef;

    protected MVPBasePresenter(VM viewModel) {
        this.viewModel = viewModel;
    }

    protected void addSubscriber(Subscription subscription) {
        if (subscriptionList == null) {
            subscriptionList = new CompositeSubscription();
        }
        subscriptionList.add(subscription);
    }

    public void onDestroy() {
        if (subscriptionList != null) {
            subscriptionList.clear();
        }
        subscriptionList = null;
    }

    @Override
    public void attachView(@NonNull V view) {
        mViewRef = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
        }
        mViewRef = null;
    }

    @Override
    public boolean isAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    @Override
    public V getView() {
        if (mViewRef != null) {
            if (mViewRef.get() != null) {
                return mViewRef.get();
            }
        }
        return null;
    }
}
