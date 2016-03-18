package ru.nekit.android.mvpmeeting.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import ru.nekit.android.mvpmeeting.presentation.model.base.IMVPModel;
import ru.nekit.android.mvpmeeting.presentation.presenter.vo.RepositoryVO;

/**
 * Created by ru.nekit.android on 08.03.16.
 */
public interface IGithubRepositoryListModel extends IMVPModel, Parcelable {

    void setRepositoryList(List<RepositoryVO> list);
    List<RepositoryVO> getRepositoryList();

    @Override
    int describeContents();

    @Override
    void writeToParcel(Parcel dest, int flags);
}
