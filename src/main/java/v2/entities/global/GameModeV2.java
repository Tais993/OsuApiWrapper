package v2.entities.global;

public enum GameModeV2 {
    FRUITS("fruits", "osu!catch"),
    MANIA("mania", "osu!mania"),
    OSU("osu", "osu!standard"),
    TAIKO("taiko", "osu!taiko");

    private final String name;
    private final String description;

    GameModeV2(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
