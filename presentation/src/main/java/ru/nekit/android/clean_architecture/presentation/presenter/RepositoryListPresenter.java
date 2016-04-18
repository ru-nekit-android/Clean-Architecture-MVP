package ru.nekit.android.clean_architecture.presentation.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;

import ru.nekit.android.clean_architecture.data.utils.rx.RxTransformers;
import ru.nekit.android.clean_architecture.domain.interactors.RequestRepositoryListUseCase;
import ru.nekit.android.clean_architecture.presentation.core.presenter.LcePresenter;
import ru.nekit.android.clean_architecture.presentation.core.presenter.viewState.LceViewState;
import ru.nekit.android.clean_architecture.presentation.di.scope.RepositoryListScope;
import ru.nekit.android.clean_architecture.presentation.model.IRepositoryListViewModel;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;
import ru.nekit.android.clean_architecture.presentation.presenter.mapper.RepositoryToViewModelMapper;
import ru.nekit.android.clean_architecture.presentation.view.fragments.IRepositoryListView;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
@RepositoryListScope
public class RepositoryListPresenter extends LcePresenter<IRepositoryListView, IRepositoryListViewModel> {

    private final RequestRepositoryListUseCase mRequestRepositoryUseCase;
    private final RepositoryToViewModelMapper mMapper;

    @Inject
    public RepositoryListPresenter(IRepositoryListViewModel model, RequestRepositoryListUseCase useCase, RepositoryToViewModelMapper mapper) {
        super(model);
        mRequestRepositoryUseCase = useCase;
        mMapper = mapper;
    }

    private void onBeforeLoad() {
        setAndApplyViewState(LceViewState.LOADING);
    }

    private void onAfterLoad() {
        //do nothing because after that the state of a view will be set to CONTENT state that automatically hides a loading
    }

    private void onResult(List<RepositoryVO> result) {
        withViewModel(model -> model.setRepositoriesList(result));
        setAndApplyViewState(LceViewState.CONTENT);
    }

    private void onError(Throwable error) {
        withViewModel(model -> model.setError(error));
        setAndApplyViewState(LceViewState.ERROR);
    }

    private void setAndApplyViewState(LceViewState state) {
        setViewState(state);
        applyViewState();
    }

    public void onSearchClick() {
        withViewAndModel((view, model) -> {
            String userName = view.getUserName();
            if (TextUtils.isEmpty(userName)) {
                return;
            }
            model.setUserName(userName);
            setAndApplyViewState(LceViewState.LOADING);
            performLoad(userName);
        });
    }

    private void performLoad(String userName) {
        addSubscriber(mRequestRepositoryUseCase.execute(userName)
                .map(mMapper)
                .compose(
                        RxTransformers.applyOperationBeforeAndAfter(this::onBeforeLoad, this::onAfterLoad)
                )
                .subscribe(this::onResult, this::onError));
    }

    public void onCreate(@Nullable Bundle savedState) {
        //no-op
    }

    public void onSaveInstanceState(Bundle outState) {
        //no-op
    }

    @Override
    public void onAttachView(@NonNull IRepositoryListView view) {
        applyViewState();
        withViewModel(model -> {
            if (getViewState() == LceViewState.LOADING) {
                performLoad(model.getUserName());
            }
        });
    }

    public void selectRepository(RepositoryVO repository) {
        IRepositoryListView view = getView();
        if (view != null) {
            view.showRepository(repository);
        }
    }

    @Override

    public void applyViewState() {
        withViewAndModel((view, model) -> {
            LceViewState state = getViewState();
            if (state == LceViewState.CONTENT && model.getRepositoriesList().isEmpty()) {
                state = LceViewState.EMPTY;
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
        });
    }
}
