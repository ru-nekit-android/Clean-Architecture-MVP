package ru.nekit.android.clean_architecture.presentation.core.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ru.nekit.android.clean_architecture.presentation.core.model.IMVPViewModel;
import ru.nekit.android.clean_architecture.presentation.core.view.IMVPView;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by ru.nekit.android on 26.05.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PresenterLifeCircleDelegateTest {

    @Mock
    public MVPPresenter<IMVPView, IMVPViewModel> presenter;
    @Mock
    public IMVPView view;
    public PresenterLifeCircleDelegate<MVPPresenter> target;

    @Before
    public void setUp() {
        target = new PresenterLifeCircleDelegate<>();
        target.onCreate(presenter, any());
    }

    @Test
    public void onCreateTest() {
        verify(presenter).onCreate(any());
    }

    @Test
    public void onDestroyTest() {
        target.onDestroy();
        verify(presenter).onDestroy();
    }

    @Test
    public void onDestroyAfterResumeTest() {
        target.onResume();
        target.onDestroy();
        verify(presenter).onDestroy();
    }

    @Test
    public void onCreateViewTest() {
        target.onCreateView(view, any());
        verify(presenter).attachView(view);
        verify(presenter).onAttachView(view);
    }

    @Test
    public void onSaveInstanceStateTest() {
        target.onSaveInstanceState(any());
        verify(presenter).onSaveInstanceState(any());
    }

    @Test
    public void onDestroyViewTest() {
        target.onDestroyView();
        verify(presenter).detachView();
    }

}
