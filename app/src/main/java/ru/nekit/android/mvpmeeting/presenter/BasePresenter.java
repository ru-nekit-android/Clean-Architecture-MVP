package ru.nekit.android.mvpmeeting.presenter;

import ru.nekit.android.mvpmeeting.model.IModel;
import ru.nekit.android.mvpmeeting.model.Model;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
public abstract class BasePresenter implements IPresenter {

    protected final IModel model;
    private final CompositeSubscription subscriptionList;

    protected BasePresenter() {
        model = new Model();
        subscriptionList = new CompositeSubscription();
    }

    protected void addSubscriber(Subscription subscription) {
        subscriptionList.add(subscription);
    }

    @Override
    public void onStop() {
        subscriptionList.clear();
    }
}
