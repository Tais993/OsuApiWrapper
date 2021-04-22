package v1.entities.global;

public enum ModeV1 {
    OSU(0, "osu!"),
    TAIKO(1, "Taiko"),
    CTB(2, "CtB"),
    MANIA(3, "osu!mania");

    private final int id;
    private final String title;

    ModeV1(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static ModeV1 getById(int id) {
        for (ModeV1 modeV1 : ModeV1.values()) {
            if (modeV1.getId() == id) {
                return modeV1;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
