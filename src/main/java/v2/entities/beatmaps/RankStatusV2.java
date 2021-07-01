package v2.entities.beatmaps;

public enum RankStatusV2 {
    GRAVEYARD(-2, "graveyard"),
    WIP(-1, "wip"),
    PENDING(0, "pending"),
    RANKED(1, "ranked"),
    APPROVED(2, "approved"),
    QUALIFIED(3, "qualified"),
    LOVED(4, "loved");

    private final int id;
    private final String name;

    RankStatusV2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static RankStatusV2 getById(int id) {
        for (RankStatusV2 rankStatusV2 : RankStatusV2.values()) {
            if (rankStatusV2.getId() == id) {
                return rankStatusV2;
            }
        }
        return null;
    }
}