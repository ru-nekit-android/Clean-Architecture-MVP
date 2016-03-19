package ru.nekit.android.mvpmeeting.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

import ru.nekit.android.mvpmeeting.presentation.model.base.IViewModel;
import ru.nekit.android.mvpmeeting.presentation.model.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.presentation.presenter.viewState.LCEViewState;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
public interface IGithubViewModel extends IViewModel, Parcelable {

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

    void setUserName(String userName);
    String getUserName();
}
