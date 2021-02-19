package v1.entities.beatmap;

public enum Genre {
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

    Genre(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static Genre getById(int id) {
        for (Genre genre : Genre.values()) {
            if (genre.getId() == id) {
                return genre;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
