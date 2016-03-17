package ru.nekit.android.mvpmeeting.presentation.presenter;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.nekit.android.mvpmeeting.presentation.model.GithubRepositoryListModel;
import ru.nekit.android.mvpmeeting.presentation.model.IGithubRepositoryListModel;
import ru.nekit.android.mvpmeeting.domain.interactors.ObtainRepositoriesInteractor;
import ru.nekit.android.mvpmeeting.presentation.presenter.base.MVPBasePresenter;
import ru.nekit.android.mvpmeeting.presentation.presenter.mapper.RepositoryToModelMapper;
import ru.nekit.android.mvpmeeting.presentation.presenter.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.model.utils.rx.RxTransformers;
import ru.nekit.android.mvpmeeting.presentation.view.fragments.IRepositoryListView;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class RepositoryListPresenter extends MVPBasePresenter<IRepositoryListView, IGithubRepositoryListModel> {

    private static final String BUNDLE_REPOSITORY_LIST_KEY = "repository_list_key";

    //DI
    private ObtainRepositoriesInteractor mInteractor;
    private RepositoryToModelMapper mMapper;

    @Inject
    public RepositoryListPresenter(GithubRepositoryListModel model, RepositoryToModelMapper mapper, ObtainRepositoriesInteractor interactor) {
        super(model);
        mMapper = mapper;
        mInteractor = interactor;
    }

    private void beforeLoad() {
        IRepositoryListView view = getView();
        if (isAttached()) {
            getModel().setRepositoryList(null);
            view.showContent();
            view.showLoading();
        }
    }

    private void afterLoad() {
        IRepositoryListView view = getView();
        if (isAttached()) {
            view.hideLoading();
        }
    }

    @Override
    public IGithubRepositoryListModel getModel() {
        return model;
    }

    public void onSearchClick() {
        IRepositoryListView view = getView();
        if (isAttached()) {
            String userName = view.getUserName();
            if (TextUtils.isEmpty(userName)) return;
            mInteractor.setUserName(userName);
            addSubscriber(mInteractor.execute()
                    .map(mMapper)
                    .compose(RxTransformers.applyOperationBeforeAndAfter(this::beforeLoad, this::afterLoad))
                    .subscribe(result -> {
                        if (isAttached()) {
                            if (result != null && !result.isEmpty()) {
                                getModel().setRepositoryList(result);
                                view.showContent();
                            } else {
                                view.showEmptyList();
                            }
                        }
                    }, error -> {
                        if (isAttached()) {
                            view.showError(error);
                            view.showEmptyList();
                        }
                    }));
        }
    }

    private boolean isRepoListEmpty() {
        List<RepositoryVO> list = getModel().getRepositoryList();
        return list == null || list.isEmpty();
    }

    public void onCreate(Bundle savedState) {
        IRepositoryListView view = getView();
        if (isAttached()) {
            if (savedState != null) {
                List<RepositoryVO> repositoryList = (List<RepositoryVO>) savedState.getSerializable(BUNDLE_REPOSITORY_LIST_KEY);
                getModel().setRepositoryList(repositoryList);
                view.showContent();
            } else {
                view.showEmptyList();
            }
            view.setData(getModel());
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        if (!isRepoListEmpty()) {
            outState.putSerializable(BUNDLE_REPOSITORY_LIST_KEY, new ArrayList<>(getModel().getRepositoryList()));
        }
    }

    public void selectRepository(RepositoryVO repository) {
        if (isAttached()) {
            getView().showRepository(repository);
        }
    }
}
