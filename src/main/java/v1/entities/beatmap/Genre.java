package v1.entities.beatmap;

import org.jetbrains.annotations.Nullable;

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

    Genre(final int id, final String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static @Nullable Genre getById(final int id) {
        for (final Genre genreV1 : Genre.values()) {
            if (genreV1.id == id) {
                return genreV1;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
