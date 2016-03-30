package ru.nekit.android.clean_architecture.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

import ru.nekit.android.clean_architecture.presentation.model.base.IModel;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;
import ru.nekit.android.clean_architecture.presentation.presenter.viewState.LCEViewState;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
public interface IGithubModel extends IModel, Parcelable {

    List<RepositoryVO> getRepositoriesList();

    void setRepositoriesList(List<RepositoryVO> list);

    Throwable getError();

    void setError(Throwable error);

    @NonNull
    LCEViewState getViewState();

    void setViewState(LCEViewState state);

    @Override
    int describeContents();

    @Override
    void writeToParcel(Parcel dest, int flags);

    String getUserName();

    void setUserName(String userName);
}