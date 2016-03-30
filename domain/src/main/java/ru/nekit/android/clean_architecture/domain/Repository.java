package ru.nekit.android.clean_architecture.domain;

/**
 * Created by ru.nekit.android on 17.03.16.
 */
public class Repository {

    public String name;
    public String ownerName;
    public String description;
    public int startCount;
    public int forks;
    public int watchers;

    public Repository(String name, String ownerName, String description, int starCount, int forks, int watchers) {
        this.name = name;
        this.ownerName = ownerName;
        this.description = description;
        this.startCount = starCount;
        this.forks = forks;
        this.watchers = watchers;
    }

}
