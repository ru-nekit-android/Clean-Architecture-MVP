package ru.nekit.android.clean_architecture.presentation.view.fragments;

import ru.nekit.android.clean_architecture.presentation.model.IGithubModel;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;
import ru.nekit.android.clean_architecture.presentation.view.base.ILCEView;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public interface IRepositoryListView extends ILCEView<IGithubModel, Throwable> {

    String getUserName();

    void showEmptyList();

    void showRepository(RepositoryVO repository);
}
