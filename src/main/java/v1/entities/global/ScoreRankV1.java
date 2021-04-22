package v1.entities.global;

public enum ScoreRankV1 {
    SSH("XH"),
    SS("SS"),
    SH("SH"),
    S("S"),
    A("A"),
    B("B"),
    C("C"),
    D("D");

    private final String rank;

    ScoreRankV1(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public static ScoreRankV1 getRankByString(String rankAsString) {
        for (ScoreRankV1 scoreRankV1 : ScoreRankV1.values()) {
            if (scoreRankV1.getRank().equals(rankAsString)) {
                return scoreRankV1;
            }
        }
        return null;
    }
}
