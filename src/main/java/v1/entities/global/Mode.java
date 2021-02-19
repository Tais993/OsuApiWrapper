package v1.entities.global;

public enum Mode {
    OSU(0, "osu!"),
    TAIKO(1, "Taiko"),
    CTB(2, "CtB"),
    MANIA(3, "osu!mania");

    private final int id;
    private final String title;

    Mode(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static Mode getById(int id) {
        for (Mode mode : Mode.values()) {
            if (mode.getId() == id) {
                return mode;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
