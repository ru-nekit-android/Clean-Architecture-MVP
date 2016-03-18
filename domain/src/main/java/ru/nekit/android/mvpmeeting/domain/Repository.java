package ru.nekit.android.mvpmeeting.domain;

/**
 * Created by MacOS on 17.03.16.
 */
public class Repository {

    private String name;
    private String ownerName;

    public Repository(String name, String ownerName) {
        this.name = name;
        this.ownerName = ownerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String toString() {
        return name + " : " + ownerName;
    }
}
