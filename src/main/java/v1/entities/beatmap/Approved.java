package v1.entities.beatmap;

public enum Approved {
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

    Approved(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static Approved fromId(int id) {
        for (Approved approved : Approved.values()) {
            if (approved.getId() == id) {
                return approved;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
