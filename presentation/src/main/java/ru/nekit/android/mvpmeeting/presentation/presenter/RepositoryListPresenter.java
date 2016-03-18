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
import ru.nekit.android.mvpmeeting.presentation.model.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.presentation.presenter.base.MVPBasePresenter;
import ru.nekit.android.mvpmeeting.presentation.presenter.mapper.RepositoryToModelMapper;
import ru.nekit.android.mvpmeeting.presentation.view.base.IStateableLCEView;
import ru.nekit.android.mvpmeeting.presentation.view.fragments.IRepositoryListView;

import static ru.nekit.android.mvpmeeting.presentation.view.base.IStateableLCEView.LCEViewState.CONTENT;
import static ru.nekit.android.mvpmeeting.presentation.view.base.IStateableLCEView.LCEViewState.ERROR;
import static ru.nekit.android.mvpmeeting.presentation.view.base.IStateableLCEView.LCEViewState.LOADING;

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
        setState(LOADING);
    }

    private void onAfterLoad() {
        //do nothing because after that the state of view will be set to CONTENT that automatically hide loading
    }

    private void onResult(List<RepositoryVO> result) {
        getModel().setRepositoriesList(result);
        setState(CONTENT);
    }

    private void onError(Throwable error) {
        getModel().setError(error);
        setState(ERROR);
    }

    private void setState(IStateableLCEView.LCEViewState state) {
        getModel().setViewState(state);
        if (isAttached()) {
            getView().applyState();
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

    public void onCreate(@Nullable Bundle savedState) {
        if (savedState != null) {
            model = savedState.getParcelable(BUNDLE_REPOSITORY_VIEW_MODEL_KEY);
        }
        if (isAttached()) {
            getView().applyState();
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
