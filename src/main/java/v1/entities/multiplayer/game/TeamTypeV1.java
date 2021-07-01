package v1.entities.multiplayer.game;

public enum TeamTypeV1 {
    HEAD_TO_HEAD(0, "Head to head"),
    TAG_CO_OP(1, "Tag Co-op"),
    TEAM_VS(2, "Team vs"),
    TAG_TEAM_VS(3, "Tag Team vs");

    private final int id;
    private final String name;

    TeamTypeV1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static TeamTypeV1 getById(int id) {
        for (TeamTypeV1 teamTypeV1 : TeamTypeV1.values()) {
            if (teamTypeV1.getId() == id) {
                return teamTypeV1;
            }
        }
        return null;
    }
}
