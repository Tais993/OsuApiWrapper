package v1.entities.beatmap;

public enum GenreV1 {
    ANY(0, "any"),
    UNSPECIFIED(1, "unspecified"),
    VIDEO_GAME(2, "video game"),
    ANIME(3, "anime"),
    ROCK(4, "rock"),
    POP(5, "pop"),
    OTHER(6, "other"),
    NOVELTY(7, "novelty"),
    HIPHOP(9, "hiphop"),
    ELECTRONIC(10, "electronic"),
    METAL(11, "metal"),
    CLASSICAL(12, "classical"),
    FOLK(13, "folk"),
    JAZZ(14, "jazz");

    private final int id;
    private final String title;

    GenreV1(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static GenreV1 getById(int id) {
        for (GenreV1 genreV1 : GenreV1.values()) {
            if (genreV1.getId() == id) {
                return genreV1;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
