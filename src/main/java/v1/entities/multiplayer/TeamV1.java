package v1.entities.multiplayer;

public enum TeamV1 {
    NONE(0),
    BLUE(1),
    RED(2);

    private final int id;

    TeamV1(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TeamV1 getById(int id) {
        for (TeamV1 teamV1 : TeamV1.values()) {
            if (teamV1.getId() == id) {
                return teamV1;
            }
        }

        return null;
    }
}
