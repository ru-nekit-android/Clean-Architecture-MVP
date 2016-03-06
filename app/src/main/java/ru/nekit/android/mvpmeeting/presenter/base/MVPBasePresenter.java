package ru.nekit.android.mvpmeeting.presenter.base;

import java.lang.ref.WeakReference;

import ru.nekit.android.mvpmeeting.model.base.IMVPModel;
import ru.nekit.android.mvpmeeting.view.base.IMVPView;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
public abstract class MVPBasePresenter<V extends IMVPView> implements IMVPPresenter<V> {

    protected final IMVPModel model;
    private CompositeSubscription subscriptionList;
    private WeakReference<IMVPView> mViewRef;

    protected MVPBasePresenter(IMVPModel model) {
        this.model = model;
        subscriptionList = new CompositeSubscription();
    }

    protected void addSubscriber(Subscription subscription) {
        subscriptionList.add(subscription);
    }

    public void onDestroy() {
        if (subscriptionList != null) {
            subscriptionList.clear();
        }
        subscriptionList = null;
    }

    @Override
    public void attachView(IMVPView view) {
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

    protected IMVPView getView() {
        if (mViewRef != null) {
            if (mViewRef.get() != null) {
                return mViewRef.get();
            }
        }
        return null;
    }
}
