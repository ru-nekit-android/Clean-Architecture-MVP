package ru.nekit.android.clean_architecture.presentation.model.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ru.nekit.android on 04.03.16.
 */
public class RepositoryVO implements Parcelable {

    public static final Parcelable.Creator<RepositoryVO> CREATOR = new Parcelable.Creator<RepositoryVO>() {

        @Override
        public RepositoryVO createFromParcel(Parcel source) {
            return new RepositoryVO(source.readInt(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString());
        }

        @Override
        public RepositoryVO[] newArray(int size) {
            return new RepositoryVO[size];
        }
    };

    private final int id;
    private final String repoName;
    private final String ownerName;
    private final String description;
    private final String starsCount;
    private final String forksCount;
    private final String watchersCount;

    public RepositoryVO(int id, String repoName, String ownerName, String description, String starCount, String forksCount, String watchersCount) {
        this.id = id;
        this.repoName = repoName;
        this.ownerName = ownerName;
        this.description = description;
        this.starsCount = starCount;
        this.forksCount = forksCount;
        this.watchersCount = watchersCount;
    }

    public int getId() {
        return id;
    }

    public String getRepoName() {
        return repoName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getDescription() {
        return description;
    }

    public String getStarsCount() {
        return starsCount;
    }

    public String getForksCount() {
        return forksCount;
    }

    public String getWatchersCount() {
        return watchersCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(repoName);
        dest.writeString(ownerName);
        dest.writeString(description);
        dest.writeString(starsCount);
        dest.writeString(forksCount);
        dest.writeString(watchersCount);
    }
}
