package ru.nekit.android.mvpmeeting.presenter;

import android.os.Bundle;

import ru.nekit.android.mvpmeeting.model.IMVPModel;
import ru.nekit.android.mvpmeeting.model.GithubModel;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
public abstract class MVPBasePresenter {

    protected final IMVPModel model;
    private final CompositeSubscription subscriptionList;

    protected MVPBasePresenter() {
        model = createModel();
        subscriptionList = new CompositeSubscription();
    }

    protected abstract IMVPModel createModel();

    protected void addSubscriber(Subscription subscription) {
        subscriptionList.add(subscription);
    }

    public abstract void onLoadState(Bundle savedState);
    public abstract void onSaveState(Bundle outSate);

    public void onStop() {
        subscriptionList.clear();
    }
}
