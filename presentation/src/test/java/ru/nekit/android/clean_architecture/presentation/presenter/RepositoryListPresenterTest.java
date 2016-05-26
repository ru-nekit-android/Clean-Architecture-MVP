package ru.nekit.android.clean_architecture.presentation.presenter;

import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import ru.nekit.android.clean_architecture.domain.entities.RepositoryEntity;
import ru.nekit.android.clean_architecture.domain.interactors.RequestRepositoryListUseCase;
import ru.nekit.android.clean_architecture.presentation.core.presenter.viewState.LceViewState;
import ru.nekit.android.clean_architecture.presentation.model.IRepositoryListViewModel;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;
import ru.nekit.android.clean_architecture.presentation.presenter.mapper.RepositoryEntityToVOMapper;
import ru.nekit.android.clean_architecture.presentation.tools.BaseTest;
import ru.nekit.android.clean_architecture.presentation.view.fragments.IRepositoryListView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static rx.Observable.error;
import static rx.Observable.just;

/**
 * Created by ru.nekit.android on 05.05.16.
 */
public class RepositoryListPresenterTest extends BaseTest {

    private static final String USER_NAME = "ru-nekit-android";

    @Mock
    private IRepositoryListView view;
    @Mock
    private IRepositoryListViewModel model;
    @Mock
    private RequestRepositoryListUseCase interactor;

    private List<RepositoryEntity> repositoryEntityList;
    private List<RepositoryVO> repositoryVOList;

    @Mock
    private RepositoryEntityToVOMapper mapper;

    private RepositoryListPresenter target;

    public void setUp() {
        initMocks(this);
        target = new RepositoryListPresenter(model, interactor, mapper);

        repositoryEntityList = new ArrayList<>();
        repositoryEntityList.add(new RepositoryEntity(5301791, "abs-search-view", USER_NAME, "", "1", "1", "1"));
        repositoryEntityList.add(new RepositoryEntity(5528510, "GIS", USER_NAME, "", "1", "1", "1"));

        repositoryVOList = new ArrayList<>();
        repositoryVOList.add(new RepositoryVO(5301791, "abs-search-view", USER_NAME, "", "1", "1", "1"));
        repositoryVOList.add(new RepositoryVO(5528510, "GIS", USER_NAME, "", "1", "1", "1"));

        when(mapper.call(repositoryEntityList)).thenReturn(repositoryVOList);

        when(view.getUserName()).thenReturn(USER_NAME);

        doAnswer(invocation -> just(repositoryEntityList))
                .when(interactor)
                .execute(USER_NAME);

        doAnswer(invocation -> repositoryVOList)
                .when(model)
                .getRepositoriesList();

        when(model.getUserName()).thenReturn(USER_NAME);
        when(model.getActualUserName()).thenReturn(USER_NAME);
    }

    @Test
    public void createPresenterTest() {
        target.onCreate(any());
        target.attachView(view);
        assertNotNull(target.getView());
        assertNotNull(target.getViewModel());
        target.onAttachView(target.getView());
        assertEquals(LceViewState.EMPTY, target.getViewState());
        //apply state
        verify(view).hideLoading();
        verify(view).showEmptyList();
        verify(view).setUserName(anyString());
    }

    @Test
    public void requestRepositoriesTest() {
        target.onCreate(any());
        target.attachView(view);
        target.onSearchClick();

        assertEquals(LceViewState.CONTENT, target.getViewState());

        verify(view).hideContent();
        verify(view).showLoading();

        verify(view).hideLoading();
        verify(view, atLeastOnce()).showContent(model);

        verify(model).setRepositoriesList(repositoryVOList);

        assertEquals(view.getUserName(), model.getUserName());
        assertEquals(view.getUserName(), model.getActualUserName());

    }

    @Test
    public void requestRepositoriesEmptyResultTest() {
        repositoryEntityList = new ArrayList<RepositoryEntity>();
        repositoryVOList = new ArrayList<RepositoryVO>();
        when(mapper.call(repositoryEntityList)).thenReturn(repositoryVOList);
        when(interactor
                .execute(USER_NAME)).thenReturn(just(repositoryEntityList));
        when(model.getRepositoriesList()).thenReturn(repositoryVOList);

        target.onCreate(any());
        target.attachView(view);

        target.onSearchClick();

        verify(view, atLeastOnce()).hideLoading();
        verify(view).showEmptyList();

    }

    @Test
    public void requestRepositoriesWithEmptyUserNameTest() {
        target.onCreate(any());
        target.attachView(view);

        when(view.getUserName()).thenReturn("");
        target.onSearchClick();

        verify(model, never()).setUserName(anyString());

        verify(view, never()).hideContent();
        verify(view, never()).showLoading();

        assertNotEquals(view.getUserName(), model.getUserName());
        assertNotEquals(view.getUserName(), model.getActualUserName());

    }

    @Test
    public void requestRepositoriesWithErrorTest() {
        target.onCreate(any());
        target.attachView(view);

        Throwable throwable = new Throwable("error");

        when(model.getError()).thenReturn(throwable);

        doAnswer(invocation -> error(throwable))
                .when(interactor)
                .execute(USER_NAME);

        target.onSearchClick();
        assertEquals(LceViewState.ERROR, target.getViewState());

        verify(view).hideLoading();
        verify(view).showEmptyList();
        verify(view).showError(model.getError());

    }

    @Test
    public void selectRepositoryTest() {
        target.onCreate(any());
        target.attachView(view);
        RepositoryVO repository = mock(RepositoryVO.class);
        when(repository.getId()).thenReturn(1);
        target.selectRepository(repository);
        verify(view).showRepository(repository.getId());
    }
    
    public void tearDown() {
        target.detachView();
        target.onDestroy();
    }
}
