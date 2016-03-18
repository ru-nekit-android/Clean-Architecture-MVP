package ru.nekit.android.mvpmeeting.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import ru.nekit.android.mvpmeeting.presentation.model.vo.RepositoryVO;
import ru.nekit.android.mvpmeeting.presentation.view.base.IStateableLceView;

/**
 * Created by ru.nekit.android on 02.03.16.
 */
public class GithubViewModel implements IGithubViewModel {

    private List<RepositoryVO> mRepositoryList;
    private Throwable mError;
    private IStateableLceView.State mState = IStateableLceView.State.EMPTY;

    @Inject
    public GithubViewModel() {
    }

    @Override
    public void setRepositoriesList(List<RepositoryVO> value) {
        mRepositoryList = value;
    }

    @Override
    public List<RepositoryVO> getRepositoriesList() {
        return mRepositoryList;
    }

    @Override
    public void setError(Throwable error) {
        mError = error;
    }

    @Override
    public Throwable getError() {
        return mError;
    }

    @Override
    public void setState(IStateableLceView.State state) {
        mState = state;
    }

    @Override
    @NonNull
    public IStateableLceView.State getState() {
        return mState;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(mState);
        dest.writeSerializable(mError);
        dest.writeList(mRepositoryList);
    }

    public static final Parcelable.Creator<GithubViewModel> CREATOR = new Parcelable.Creator<GithubViewModel>() {

        public GithubViewModel createFromParcel(Parcel source) {
            GithubViewModel model = new GithubViewModel();
            model.mState = (IStateableLceView.State) source.readSerializable();
            model.mError = (Throwable) source.readSerializable();
            source.readTypedList(model.mRepositoryList, RepositoryVO.CREATOR);
            return model;
        }

        public GithubViewModel[] newArray(int size) {
            return new GithubViewModel[size];
        }

    };
}
