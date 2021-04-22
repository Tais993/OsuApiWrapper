package v1.entities.beatmap;

public enum ApprovedV1 {
    ANY(-2, "graveyard"),
    UNSPECIFIED(-1, "WIP"),
    VIDEO_GAME(0, "pending"),
    ANIME(1, "ranked"),
    ROCK(2, "approved"),
    POP(3, "qualified"),
    OTHER(4, "loved");

    //  // 4 = loved, 3 = qualified, 2 = approved, 1 = ranked, 0 = pending, -1 = WIP, -2 = graveyard

    private final int id;
    private final String title;

    ApprovedV1(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static ApprovedV1 fromId(int id) {
        for (ApprovedV1 approvedV1 : ApprovedV1.values()) {
            if (approvedV1.getId() == id) {
                return approvedV1;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
