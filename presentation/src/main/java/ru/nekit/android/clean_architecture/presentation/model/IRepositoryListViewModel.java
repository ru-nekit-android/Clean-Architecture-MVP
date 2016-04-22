package ru.nekit.android.clean_architecture.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import ru.nekit.android.clean_architecture.presentation.core.model.IMVPViewModel;
import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
public interface IRepositoryListViewModel extends IMVPViewModel, Parcelable {

    List<RepositoryVO> getRepositoriesList();

    void setRepositoriesList(List<RepositoryVO> list);

    Throwable getError();

    void setError(Throwable error);

    @Override
    int describeContents();

    @Override
    void writeToParcel(Parcel dest, int flags);

    String getUserName();

    void setUserName(String userName);

    String getActualUserName();
}
