package ru.nekit.android.mvpmeeting.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

import ru.nekit.android.mvpmeeting.presentation.model.base.IViewModel;
import ru.nekit.android.mvpmeeting.presentation.model.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.presentation.view.base.IStateableLCEView;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
public interface IGithubViewModel extends IViewModel, Parcelable {

    void setRepositoriesList(List<RepositoryVO> list);
    List<RepositoryVO> getRepositoriesList();

    void setError(Throwable error);
    Throwable getError();

    void setState(IStateableLCEView.LCEViewState state);
    @NonNull
    IStateableLCEView.LCEViewState getState();

    @Override
    int describeContents();

    @Override
    void writeToParcel(Parcel dest, int flags);

}
