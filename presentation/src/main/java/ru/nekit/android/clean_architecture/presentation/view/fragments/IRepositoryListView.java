package ru.nekit.android.clean_architecture.presentation.view.fragments;

import ru.nekit.android.clean_architecture.presentation.core.view.ILCEView;
import ru.nekit.android.clean_architecture.presentation.model.IRepositoryListViewModel;

/**
 * Created by ru.nekit.android on 05.03.16.
 */
public interface IRepositoryListView extends ILCEView<IRepositoryListViewModel, Throwable> {

    String getUserName();

    void showEmptyList();

    void showRepository(int repositoryId);

    void setUserName(String value);
}

