package v1.entities.beatmap;

public enum Language {
    ANY(0, "any"),
    UNSPECIFIED(1, "unspecified"),
    ENGLISH(2, "english"),
    JAPANESE(3, "japanese"),
    CHINESE(4, "chinese"),
    INSTRUMENTAL(5, "instrumental"),
    KOREAN(6, "korean"),
    FRENCH(7, "french"),
    GERMAN(8, "german"),
    SWEDISH(9, "swedish"),
    SPANISH(10, "spanish"),
    ITALIAN(11, "italian"),
    RUSSIAN(12, "russian"),
    POLISH(13, "polish"),
    OTHER(14, "other");

    private final int id;
    private final String title;

    Language(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static Language getById(int id) {
        for (Language languageV1 : Language.values()) {
            if (languageV1.getId() == id) {
                return languageV1;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
