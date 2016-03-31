package ru.nekit.android.clean_architecture.presentation.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.List;

import ru.nekit.android.clean_architecture.data.utils.rx.RxTransformers;
import ru.nekit.android.clean_architecture.domain.interactors.ObtainRepositoriesInteractor;
import ru.nekit.android.clean_architecture.presentation.model.IGithubModel;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;
import ru.nekit.android.clean_architecture.presentation.presenter.base.MVPBasePresenter;
import ru.nekit.android.clean_architecture.presentation.presenter.mapper.RepositoryToModelMapper;
import ru.nekit.android.clean_architecture.presentation.presenter.viewState.IStateable;
import ru.nekit.android.clean_architecture.presentation.presenter.viewState.LCEViewState;
import ru.nekit.android.clean_architecture.presentation.view.fragments.IRepositoryListView;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class RepositoryListPresenter extends MVPBasePresenter<IRepositoryListView, IGithubModel> implements IStateable<LCEViewState> {

    private static final String BUNDLE_REPOSITORY_VIEW_MODEL_KEY = "bundle_repository_view_model_key";

    private final ObtainRepositoriesInteractor mInteractor;
    private final RepositoryToModelMapper mMapper;

    public RepositoryListPresenter(IGithubModel model, ObtainRepositoriesInteractor interactor, RepositoryToModelMapper mapper) {
        super(model);
        mInteractor = interactor;
        mMapper = mapper;
    }

    private void onBeforeLoad() {
        setState(LCEViewState.LOADING);
    }

    private void onAfterLoad() {
        //do nothing because after that the state of a view will be set to CONTENT state that automatically hides a loading
    }

    private void onResult(List<RepositoryVO> result) {
        getModel().setRepositoriesList(result);
        setState(LCEViewState.CONTENT);
    }

    private void onError(Throwable error) {
        getModel().setError(error);
        setState(LCEViewState.ERROR);
    }

    private void setState(LCEViewState state) {
        getModel().setViewState(state);
        applyState();
    }

    public void onSearchClick() {
        String userName = getView().getUserName();
        if (TextUtils.isEmpty(userName)) {
            return;
        }
        setState(LCEViewState.LOADING);
        getModel().setUserName(userName);
        performLoad();
    }

    private void performLoad() {
        String userName = getModel().getUserName();
        if (TextUtils.isEmpty(userName)) {
            return;
        }
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
        if (getModel().getViewState() == LCEViewState.LOADING) {
            performLoad();
        }
    }

    @Override
    public void applyState() {
        if (isViewAttached()) {
            IRepositoryListView view = getView();
            IGithubModel model = getModel();
            LCEViewState state = model.getViewState();
            if (state == LCEViewState.CONTENT && model.getRepositoriesList().isEmpty()) {
                state = LCEViewState.EMPTY;
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

                    view.hideLoading();
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
