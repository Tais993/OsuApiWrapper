package v1.entities.multiplayer.game;

public enum ScoringType {
    SCORE(0, "score"),
    ACCURACY(1, "accuracy"),
    COMBO(2, "combo"),
    SCORE_V2(3, "score v2");

    private final int id;
    private final String name;

    ScoringType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ScoringType getById(int id) {
        for (ScoringType scoringType : ScoringType.values()) {
            if (scoringType.getId() == id) {
                return scoringType;
            }
        }
        return null;
    }
}
