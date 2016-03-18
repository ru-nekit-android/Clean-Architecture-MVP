package ru.nekit.android.mvpmeeting.presentation.model.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
public class RepositoryVO implements Parcelable {

    private String repoName;
    private String ownerName;

    public RepositoryVO(String repoName, String ownerName) {
        this.repoName = repoName;
        this.ownerName = ownerName;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String toString() {
        return repoName + " : " + ownerName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(repoName);
    }

    public static final Parcelable.Creator<RepositoryVO> CREATOR = new Parcelable.Creator<RepositoryVO>() {

        @Override
        public RepositoryVO createFromParcel(Parcel source) {
            return new RepositoryVO(source.readString(), source.readString());
        }

        @Override
        public RepositoryVO[] newArray(int size) {
            return new RepositoryVO[size];
        }
    };
}
