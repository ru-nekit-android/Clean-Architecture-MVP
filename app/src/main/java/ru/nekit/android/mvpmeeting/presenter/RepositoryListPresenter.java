package ru.nekit.android.mvpmeeting.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import ru.nekit.android.mvpmeeting.presenter.mapper.RepositoryListMapper;
import ru.nekit.android.mvpmeeting.presenter.vo.Repository;
import ru.nekit.android.mvpmeeting.view.RepositoryListActivity;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class RepositoryListPresenter extends BasePresenter {

    private static final String BUNDLE_REPOSITORY_LIST_KEY = "repository_list_key";
    private final RepositoryListMapper mapper = new RepositoryListMapper();
    private RepositoryListActivity view;
    private List<Repository> repositoryList;

    public RepositoryListPresenter(RepositoryListActivity view) {
        super();
        this.view = view;
    }

    public void onSearchClick() {
        String userName = view.getUserName();
        if (TextUtils.isEmpty(userName)) return;
        addSubscriber(model.getRepoList(userName).map(mapper).subscribe(result -> {
            if (result != null && !result.isEmpty()) {
                repositoryList = result;
                view.showRepositoryList(result);
            } else {
                view.showEmptyList();
            }
        }, error -> {
            view.showError(error.getMessage());
        }));
    }

    private boolean isRepoListEmpty() {
        return repositoryList == null || repositoryList.isEmpty();
    }

    public void onRestoreInstanceState(Bundle savedState) {
        if (savedState != null) {
            repositoryList = (List<Repository>) savedState.getSerializable(BUNDLE_REPOSITORY_LIST_KEY);
            view.showRepositoryList(repositoryList);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isRepoListEmpty()) {
            outState.putSerializable(BUNDLE_REPOSITORY_LIST_KEY, new ArrayList<Repository>(repositoryList));
        }
    }
}
