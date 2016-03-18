package ru.nekit.android.mvpmeeting.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import javax.inject.Inject;

import ru.nekit.android.mvpmeeting.presentation.presenter.vo.RepositoryVO;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class GithubRepositoryListModel implements IGithubRepositoryListModel {

    private List<RepositoryVO> mRepositoryList;

    @Inject
    public GithubRepositoryListModel() {
    }

    @Override
    public void setRepositoryList(List<RepositoryVO> value) {
        mRepositoryList = value;
    }

    @Override
    public List<RepositoryVO> getRepositoryList() {
        return mRepositoryList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mRepositoryList);
    }

    public static final Parcelable.Creator<GithubRepositoryListModel> CREATOR = new Parcelable.Creator<GithubRepositoryListModel>() {

        public GithubRepositoryListModel createFromParcel(Parcel source) {
            GithubRepositoryListModel model = new GithubRepositoryListModel();
            source.readTypedList(model.mRepositoryList, RepositoryVO.CREATOR);
            return model;
        }

        public GithubRepositoryListModel[] newArray(int size) {
            return new GithubRepositoryListModel[size];
        }

    };
}
