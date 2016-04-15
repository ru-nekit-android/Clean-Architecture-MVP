package ru.nekit.android.clean_architecture.presentation.core.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import ru.nekit.android.clean_architecture.presentation.core.presenter.IHasPresenter;
import ru.nekit.android.clean_architecture.presentation.core.presenter.IMVPPresenter;
import ru.nekit.android.clean_architecture.presentation.core.presenter.PresenterLifeCircleDelegate;
import ru.nekit.android.clean_architecture.presentation.core.presenter.persistance.ComponentControllerDelegate;
import ru.nekit.android.clean_architecture.presentation.core.presenter.persistance.IComponentCache;
import ru.nekit.android.clean_architecture.presentation.core.presenter.persistance.IComponentFactory;
import ru.nekit.android.clean_architecture.presentation.core.view.IMVPView;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public abstract class MVPFragment<C extends IHasPresenter<P>, P extends IMVPPresenter> extends Fragment implements IMVPView {

    private final IComponentFactory<C> componentFactory = this::onCreateNonConfigurationComponent;
    private final PresenterLifeCircleDelegate<P> presenterDelegate = new PresenterLifeCircleDelegate<>();
    private final ComponentControllerDelegate<C> componentDelegate = new ComponentControllerDelegate<>();
    private IComponentCache<C> componentCache;

    @NonNull
    public final P getPresenter() {
        return getComponent().getPresenter();
    }

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IComponentCache<C> componentCache = getComponentCache();
        componentDelegate.onCreate(componentCache, savedInstanceState, componentFactory);
        presenterDelegate.onCreate(getPresenter(), savedInstanceState);
    }

    @Override
    @CallSuper
    @SuppressWarnings("unchecked")
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenterDelegate.onCreateView(this, savedInstanceState);
    }

    @Override
    @CallSuper
    public void onResume() {
        super.onResume();
        componentDelegate.onResume();
        presenterDelegate.onResume();
    }

    @Override
    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        componentDelegate.onSaveInstanceState(outState);
        presenterDelegate.onSaveInstanceState(outState);
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        super.onDestroyView();
        presenterDelegate.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        componentDelegate.onDestroy();
        presenterDelegate.onDestroy();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        if (activity instanceof IComponentCache) {
            componentCache = (IComponentCache) activity;
        } else {
            throw new RuntimeException(getClass().getSimpleName()
                    + " must be attached to " +
                    "an Activity that implements " +
                    IComponentCache.class.getSimpleName());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        componentCache = null;
    }

    protected IComponentCache<C> getComponentCache() {
        return componentCache;
    }

    public C getComponent() {
        return componentDelegate.getComponent();
    }

    protected abstract C onCreateNonConfigurationComponent();
}
