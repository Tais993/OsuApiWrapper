package v1.entities.beatmap;

public enum Approved {
    GRAVEYARD(-2, "graveyard"),
    WORK_IN_PROGRESS(-1, "WIP"),
    PENDING(0, "pending"),
    RANKED(1, "ranked"),
    APPROVED(2, "approved"),
    QUALIFIED(3, "qualified"),
    LOVED(4, "loved");

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
        for (Approved approvedV1 : Approved.values()) {
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
