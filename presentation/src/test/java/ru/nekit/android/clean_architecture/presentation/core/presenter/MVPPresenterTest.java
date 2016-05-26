package ru.nekit.android.clean_architecture.presentation.core.presenter;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ru.nekit.android.clean_architecture.presentation.core.model.IMVPViewModel;
import ru.nekit.android.clean_architecture.presentation.core.view.IMVPView;
import rx.functions.Action1;
import rx.functions.Action2;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.never;

/**
 * Created by ru.nekit.android on 26.05.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MVPPresenterTest {

    @Mock
    /**
     * Initialized in {@link MockitoJUnitRunner}.
     */
    @SuppressWarnings("NullableProblems")
    @NonNull
    private IMVPView view;
    @Mock
    /**
     * Initialized in {@link MockitoJUnitRunner}.
     */
    @SuppressWarnings("NullableProblems")
    @NonNull
    private IMVPViewModel model;
    private MVPPresenter<IMVPView, IMVPViewModel> target;

    @Before
    public void setUp() {
        target = new MVPPresenter<>(model);
    }

    @Test
    public void notNullViewAndModelTest() {
        assertNotNull(view);
        assertNotNull(model);
        assertNotNull(target);
    }

    @Test
    public void viewIsSamePresenterGetViewTest() {
        target.attachView(view);
        assertEquals(target.getView(), view);
    }

    @Test
    public void viewModelIsSamePresenterGetViewTest() {
        assertEquals(target.getViewModel(), model);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void withViewTest() {
        target.attachView(view);
        Action1<IMVPView> action = Mockito.mock(Action1.class);
        target.withView(action);
        Mockito.verify(action).call(view);
    }


    @SuppressWarnings("all")
    @Test
    public void withViewNeverTest() {
        target.attachView(null);
        Action1<IMVPView> action = Mockito.mock(Action1.class);
        target.withView(action);
        Mockito.verify(action, never()).call(view);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void withViewAndModelTest() {
        target.attachView(view);
        Action2<IMVPView, IMVPViewModel> action = Mockito.mock(Action2.class);
        target.withViewAndModel(action);
        Mockito.verify(action).call(view, model);
    }

    @SuppressWarnings("all")
    @Test
    public void withViewAndModelNeverTest() {
        target.attachView(null);
        Action2<IMVPView, IMVPViewModel> action = Mockito.mock(Action2.class);
        target.withViewAndModel(action);
        Mockito.verify(action, never()).call(view, model);
    }

    @Test
    public void onDestroyTest() {
        target.onDestroy();
        assertNull(target.getView());
        assertNull(target.getViewModel());
    }

}
