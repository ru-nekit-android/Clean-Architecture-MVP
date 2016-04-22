package ru.nekit.android.clean_architecture.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import ru.nekit.android.clean_architecture.presentation.model.vo.RepositoryVO;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class RepositoryListViewModel implements IRepositoryListViewModel {

    public static final Parcelable.Creator<RepositoryListViewModel> CREATOR = new Parcelable.Creator<RepositoryListViewModel>() {

        public RepositoryListViewModel createFromParcel(Parcel source) {
            RepositoryListViewModel model = new RepositoryListViewModel(source.readString());
            model.mUserName = source.readString();
            model.mError = (Throwable) source.readSerializable();
            source.readTypedList(model.mRepositoryList, RepositoryVO.CREATOR);
            return model;
        }

        public RepositoryListViewModel[] newArray(int size) {
            return new RepositoryListViewModel[size];
        }

    };

    private final String mDefaultUserName;
    private List<RepositoryVO> mRepositoryList;
    private Throwable mError;
    private String mUserName;

    public RepositoryListViewModel(String defaultUserName) {
        mDefaultUserName = defaultUserName;
    }

    @Override
    public List<RepositoryVO> getRepositoriesList() {
        return mRepositoryList;
    }

    @Override
    public void setRepositoriesList(List<RepositoryVO> value) {
        mRepositoryList = value;
    }

    @Override
    public Throwable getError() {
        return mError;
    }

    @Override
    public void setError(Throwable error) {
        mError = error;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDefaultUserName);
        dest.writeString(mUserName);
        dest.writeSerializable(mError);
        dest.writeList(mRepositoryList);
    }

    @Override
    public String getUserName() {
        return mUserName;
    }

    @Override
    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Override
    public String getActualUserName() {
        return mUserName == null || mUserName.isEmpty() ? mDefaultUserName : mUserName;
    }
}
