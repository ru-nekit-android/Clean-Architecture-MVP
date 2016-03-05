package ru.nekit.android.mvpmeeting.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import ru.nekit.android.mvpmeeting.model.GithubModel;
import ru.nekit.android.mvpmeeting.model.IMVPModel;
import ru.nekit.android.mvpmeeting.presenter.mapper.RepositoryListMapper;
import ru.nekit.android.mvpmeeting.presenter.vo.Repository;
import ru.nekit.android.mvpmeeting.view.fragments.IRepositoryListView;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class RepositoryListPresenter extends MVPBasePresenter {

    private static final String BUNDLE_REPOSITORY_LIST_KEY = "repository_list_key";

    private final RepositoryListMapper mMapper = new RepositoryListMapper();
    private WeakReference<IRepositoryListView> mViewRef;
    private List<Repository> mRepositoryList;

    public RepositoryListPresenter(IRepositoryListView view) {
        super();
        this.mViewRef = new WeakReference<>(view);
    }

    private GithubModel getModel() {
        return (GithubModel) model;
    }

    public void onSearchClick() {
        if (mViewRef != null) {
            IRepositoryListView view = mViewRef.get();
            if (view != null) {
                String userName = view.getUserName();
                if (TextUtils.isEmpty(userName)) return;
                addSubscriber(getModel().getRepoList(userName).map(mMapper).subscribe(result -> {
                    if (result != null && !result.isEmpty()) {
                        mRepositoryList = result;
                        view.showRepositoryList(result);
                    } else {
                        view.showEmptyList();
                    }
                }, error -> {
                    view.showError(error.getMessage());
                }));
            }
        }
    }

    private boolean isRepoListEmpty() {
        return mRepositoryList == null || mRepositoryList.isEmpty();
    }

    @Override
    protected IMVPModel createModel() {
        return new GithubModel();
    }

    public void onLoadState(Bundle savedState) {
        if (savedState != null) {
            mRepositoryList = (List<Repository>) savedState.getSerializable(BUNDLE_REPOSITORY_LIST_KEY);
            if (mViewRef != null) {
                IRepositoryListView view = mViewRef.get();
                if (view != null) {
                    view.showRepositoryList(mRepositoryList);
                }
            }
        }
    }

    public void onSaveState(Bundle outState) {
        if (!isRepoListEmpty()) {
            outState.putSerializable(BUNDLE_REPOSITORY_LIST_KEY, new ArrayList<>(mRepositoryList));
        }
    }

    public void selectRepository(Repository repository) {
        if (mViewRef != null) {
            IRepositoryListView view = mViewRef.get();
            if (view != null) {
                view.showRepository(repository);
            }
        }
    }
}
