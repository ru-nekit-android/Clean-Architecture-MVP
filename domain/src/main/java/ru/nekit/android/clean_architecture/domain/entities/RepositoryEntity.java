package ru.nekit.android.clean_architecture.domain.entities;

public class RepositoryEntity {

    private final int id;
    private final String name;
    private final String ownerName;
    private final String description;
    private final String starsCount;
    private final String forksCount;
    private final String watchersCount;

    public RepositoryEntity(int id, String name, String ownerName, String description, String starCount, String forksCount, String watchersCount) {
        this.id = id;
        this.name = name;
        this.ownerName = ownerName;
        this.description = description;
        this.starsCount = starCount;
        this.forksCount = forksCount;
        this.watchersCount = watchersCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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


}
