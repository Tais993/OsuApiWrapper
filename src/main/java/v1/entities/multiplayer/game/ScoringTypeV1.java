package v1.entities.multiplayer.game;

public enum ScoringTypeV1 {
    SCORE(0, "score"),
    ACCURACY(1, "accuracy"),
    COMBO(2, "combo"),
    SCORE_V2(3, "score v2");

    private final int id;
    private final String name;

    ScoringTypeV1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ScoringTypeV1 getById(int id) {
        for (ScoringTypeV1 scoringTypeV1 : ScoringTypeV1.values()) {
            if (scoringTypeV1.getId() == id) {
                return scoringTypeV1;
            }
        }
        return null;
    }
}
