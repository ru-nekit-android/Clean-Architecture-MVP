package ru.nekit.android.mvpmeeting.presenter;

import ru.nekit.android.mvpmeeting.model.IModel;
import ru.nekit.android.mvpmeeting.model.Model;
import ru.nekit.android.mvpmeeting.presenter.mapper.RepositoryListMapper;
import ru.nekit.android.mvpmeeting.view.IView;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class Presenter implements IPresenter {

    private IModel model = new Model();

    private IView view;

    private Subscription subscription = Subscriptions.empty();

    public Presenter(IView view) {
        this.view = view;
    }

    @Override
    public void onSearchClick() {
        unsubscribeIfNeedIt();
        subscription = model.getRepoList(view.getUserName()).map(new RepositoryListMapper()).subscribe(result -> {
            if (result != null && !result.isEmpty()) {
                view.showData(result);
            } else {
                view.showEmptyList();
            }
        }, error -> {
            view.showError(error.toString());
        });
    }

    private void unsubscribeIfNeedIt() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onStop() {
        unsubscribeIfNeedIt();
    }
}
