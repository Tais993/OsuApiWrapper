package v1.entities.global;

public enum ScoreRank {
    SSH("XH"),
    SS("SS"),
    SH("SH"),
    S("S"),
    A("A"),
    B("B"),
    C("C"),
    D("D");

    private final String rank;

    ScoreRank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public static ScoreRank getRankByString(String rankAsString) {
        for (ScoreRank scoreRank : ScoreRank.values()) {
            if (scoreRank.getRank().equals(rankAsString)) {
                return scoreRank;
            }
        }
        return null;
    }
}
