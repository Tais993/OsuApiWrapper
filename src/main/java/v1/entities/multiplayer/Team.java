package v1.entities.multiplayer;

public enum Team {
    NONE(0),
    BLUE(1),
    RED(2);

    private final int id;

    Team(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Team getById(int id) {
        for (Team team : Team.values()) {
            if (team.getId() == id) {
                return team;
            }
        }

        return null;
    }
}
