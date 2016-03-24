package ru.nekit.android.mvpmeeting.presentation.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.List;

import ru.nekit.android.mvpmeeting.domain.interactors.ObtainRepositoriesInteractor;
import ru.nekit.android.mvpmeeting.model.utils.rx.RxTransformers;
import ru.nekit.android.mvpmeeting.presentation.model.IGithubModel;
import ru.nekit.android.mvpmeeting.presentation.model.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.presentation.presenter.base.MVPBasePresenter;
import ru.nekit.android.mvpmeeting.presentation.presenter.mapper.RepositoryToModelMapper;
import ru.nekit.android.mvpmeeting.presentation.presenter.viewState.IStateable;
import ru.nekit.android.mvpmeeting.presentation.presenter.viewState.LCEViewState;
import ru.nekit.android.mvpmeeting.presentation.view.fragments.IRepositoryListView;

import static ru.nekit.android.mvpmeeting.presentation.presenter.viewState.LCEViewState.CONTENT;
import static ru.nekit.android.mvpmeeting.presentation.presenter.viewState.LCEViewState.EMPTY;
import static ru.nekit.android.mvpmeeting.presentation.presenter.viewState.LCEViewState.ERROR;
import static ru.nekit.android.mvpmeeting.presentation.presenter.viewState.LCEViewState.LOADING;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class RepositoryListPresenter extends MVPBasePresenter<IRepositoryListView, IGithubModel> implements IStateable<LCEViewState> {

    private static final String BUNDLE_REPOSITORY_VIEW_MODEL_KEY = "bundle_repository_view_model_key";

    private ObtainRepositoriesInteractor mInteractor;
    private RepositoryToModelMapper mMapper;

    public RepositoryListPresenter(IGithubModel model, ObtainRepositoriesInteractor interactor, RepositoryToModelMapper mapper) {
        super(model);
        mInteractor = interactor;
        mMapper = mapper;
    }

    private void onBeforeLoad() {
        setState(LOADING);
    }

    private void onAfterLoad() {
        //do nothing because after that the state of a view will be set to CONTENT state that automatically hides a loading
    }

    private void onResult(List<RepositoryVO> result) {
        getModel().setRepositoriesList(result);
        setState(CONTENT);
    }

    private void onError(Throwable error) {
        getModel().setError(error);
        setState(ERROR);
    }

    private void setState(LCEViewState state) {
        getModel().setViewState(state);
        applyState();
    }

    public void onSearchClick() {
        String userName = getView().getUserName();
        if (TextUtils.isEmpty(userName)) return;
        setState(LCEViewState.LOADING);
        getModel().setUserName(userName);
        performLoad();
    }

    private void performLoad() {
        String userName = getModel().getUserName();
        if (TextUtils.isEmpty(userName)) return;
        addSubscriber(mInteractor.execute(userName)
                .map(mMapper)
                .compose(
                        RxTransformers.applyOperationBeforeAndAfter(this::onBeforeLoad, this::onAfterLoad)
                )
                .subscribe(this::onResult, this::onError));
    }

    public void onCreate(@Nullable Bundle savedState) {
        if (savedState != null) {
            model = savedState.getParcelable(BUNDLE_REPOSITORY_VIEW_MODEL_KEY);
        }
        applyState();
        if (getModel().getViewState() == LOADING) {
            performLoad();
        }
    }

    @Override
    public void applyState() {
        if (isViewAttached()) {
            IRepositoryListView view = getView();
            IGithubModel model = getModel();
            LCEViewState state = model.getViewState();
            if (state == CONTENT) {
                if (model.getRepositoriesList().isEmpty()) {
                    state = EMPTY;
                }
            }
            switch (state) {
                case CONTENT:

                    view.hideLoading();
                    view.showContent(model);

                    break;

                case EMPTY:

                    view.hideLoading();
                    view.showEmptyList();

                    break;

                case LOADING:

                    view.hideContent();
                    view.showLoading();

                    break;

                case ERROR:

                    view.showEmptyList();
                    view.showError(model.getError());

                    break;

                default:
                    break;

            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(BUNDLE_REPOSITORY_VIEW_MODEL_KEY, getModel());
    }

    @Override
    public IGithubModel getModel() {
        return model;
    }

    public void selectRepository(RepositoryVO repository) {
        if (isViewAttached()) {
            getView().showRepository(repository);
        }
    }
}
