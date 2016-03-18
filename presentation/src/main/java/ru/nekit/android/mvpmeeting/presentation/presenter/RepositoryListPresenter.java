package ru.nekit.android.mvpmeeting.presentation.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;

import ru.nekit.android.mvpmeeting.domain.interactors.ObtainRepositoriesInteractor;
import ru.nekit.android.mvpmeeting.model.utils.rx.RxTransformers;
import ru.nekit.android.mvpmeeting.presentation.model.GithubViewModel;
import ru.nekit.android.mvpmeeting.presentation.model.IGithubViewModel;
import ru.nekit.android.mvpmeeting.presentation.presenter.base.MVPBasePresenter;
import ru.nekit.android.mvpmeeting.presentation.presenter.mapper.RepositoryToModelMapper;
import ru.nekit.android.mvpmeeting.presentation.model.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.presentation.view.fragments.IRepositoryListView;

import static ru.nekit.android.mvpmeeting.presentation.view.base.IStateableLceView.State.*;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class RepositoryListPresenter extends MVPBasePresenter<IRepositoryListView, IGithubViewModel> {

    private static final String BUNDLE_REPOSITORY_VIEW_MODEL_KEY = "bundle_repository_view_model_key";

    private ObtainRepositoriesInteractor mInteractor;
    private RepositoryToModelMapper mMapper;

    @Inject
    public RepositoryListPresenter(GithubViewModel model, RepositoryToModelMapper mapper, ObtainRepositoriesInteractor interactor) {
        super(model);
        mMapper = mapper;
        mInteractor = interactor;
    }

    private void onBeforeLoad() {
        IRepositoryListView view = getView();
        if (isAttached()) {
            view.setState(LOADING);
        }
    }

    private void onAfterLoad() {
        //do nothing because after that the state of view will be set to CONTENT that automatically hide loading
    }

    private void onResult(List<RepositoryVO> result) {
        IRepositoryListView view = getView();
        if (isAttached()) {
            if (result != null && !result.isEmpty()) {
                getModel().setRepositoriesList(result);
                view.setState(CONTENT);
            } else {
                view.setState(EMPTY);
            }
        }
    }

    private void onError(Throwable error) {
        IRepositoryListView view = getView();
        if (isAttached()) {
            getModel().setError(error);
            view.setState(EMPTY);
            view.setState(ERROR);
        }
    }

    @Override
    public IGithubViewModel getModel() {
        return model;
    }

    public void onSearchClick() {
        IRepositoryListView view = getView();
        if (isAttached()) {
            String userName = view.getUserName();
            if (TextUtils.isEmpty(userName)) return;
            addSubscriber(mInteractor.execute(userName)
                    .map(mMapper)
                    .compose(RxTransformers.applyOperationBeforeAndAfter(this::onBeforeLoad, this::onAfterLoad))
                    .subscribe(this::onResult, this::onError));
        }
    }

    private boolean isRepoListEmpty() {
        List<RepositoryVO> list = getModel().getRepositoriesList();
        return list == null || list.isEmpty();
    }

    public void onCreate(@Nullable Bundle savedState) {
        IRepositoryListView view = getView();
        if (isAttached()) {
            if (savedState != null) {
                model = savedState.getParcelable(BUNDLE_REPOSITORY_VIEW_MODEL_KEY);
                view.setState(model.getState());
            } else {
                view.setState(EMPTY);
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(BUNDLE_REPOSITORY_VIEW_MODEL_KEY, getModel());
    }

    public void selectRepository(RepositoryVO repository) {
        if (isAttached()) {
            getView().showRepository(repository);
        }
    }
}
