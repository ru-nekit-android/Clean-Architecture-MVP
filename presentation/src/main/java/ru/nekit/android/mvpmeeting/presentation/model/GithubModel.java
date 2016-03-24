package ru.nekit.android.mvpmeeting.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

import ru.nekit.android.mvpmeeting.presentation.model.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.presentation.presenter.viewState.LCEViewState;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class GithubModel implements IGithubModel {

    public static final Parcelable.Creator<GithubModel> CREATOR = new Parcelable.Creator<GithubModel>() {

        public GithubModel createFromParcel(Parcel source) {
            GithubModel model = new GithubModel();
            model.mUserName = source.readString();
            model.mState = (LCEViewState) source.readSerializable();
            model.mError = (Throwable) source.readSerializable();
            source.readTypedList(model.mRepositoryList, RepositoryVO.CREATOR);
            return model;
        }

        public GithubModel[] newArray(int size) {
            return new GithubModel[size];
        }

    };

    private List<RepositoryVO> mRepositoryList;
    private Throwable mError;
    private LCEViewState mState = LCEViewState.EMPTY;
    private String mUserName;

    public GithubModel() {
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
    @NonNull
    public LCEViewState getViewState() {
        return mState;
    }

    @Override
    public void setViewState(LCEViewState state) {
        mState = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUserName);
        dest.writeSerializable(mState);
        dest.writeSerializable(mError);
        dest.writeList(mRepositoryList);
    }

    @Override
    public void setUserName(String userName) {
        mUserName = userName;
    }

    @Override
    public String getUserName() {
        return mUserName;
    }
}
