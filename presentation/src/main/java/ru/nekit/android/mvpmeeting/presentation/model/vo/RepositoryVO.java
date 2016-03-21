package ru.nekit.android.mvpmeeting.presentation.model.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
public class RepositoryVO implements Parcelable {

    public static final Parcelable.Creator<RepositoryVO> CREATOR = new Parcelable.Creator<RepositoryVO>() {

        @Override
        public RepositoryVO createFromParcel(Parcel source) {
            return new RepositoryVO(source.readString(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString());
        }

        @Override
        public RepositoryVO[] newArray(int size) {
            return new RepositoryVO[size];
        }
    };

    public String repoName;
    public String ownerName;
    public String description;
    public String starsCount;
    public String forksCount;
    public String watchersCount;

    public RepositoryVO(String repoName, String ownerName, String description, String starCount, String forksCount, String watchersCount) {
        this.repoName = repoName;
        this.ownerName = ownerName;
        this.description = description;
        this.starsCount = starCount;
        this.forksCount = forksCount;
        this.watchersCount = watchersCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(repoName);
        dest.writeString(ownerName);
        dest.writeString(description);
        dest.writeString(starsCount);
        dest.writeString(forksCount);
        dest.writeString(watchersCount);
    }
}
