package v1.entities.multiplayer.game;

public enum TeamType {
    HEAD_TO_HEAD(0, "Head to head"),
    TAG_CO_OP(1, "Tag Co-op"),
    TEAM_VS(2, "Team vs"),
    TAG_TEAM_VS(3, "Tag Team vs");

    private final int id;
    private final String name;

    TeamType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static TeamType getById(int id) {
        for (TeamType teamType : TeamType.values()) {
            if (teamType.getId() == id) {
                return teamType;
            }
        }
        return null;
    }
}
