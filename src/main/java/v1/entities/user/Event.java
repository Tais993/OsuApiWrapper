package v1.entities.user;

import com.google.gson.JsonObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private final String displayHtml;

    private final long beatmapId;
    private final long beatmapSetId;

    private final LocalDateTime date;

    private final int epicFactor;

    public Event(JsonObject json) {
        System.out.println(json);
        this.displayHtml = json.get("display_html").getAsString();

        this.beatmapId = json.get("beatmap_id").isJsonNull() ? 0 : json.get("beatmap_id").getAsLong();
        this.beatmapSetId = json.get("beatmapset_id").isJsonNull() ? 0 : json.get("beatmapset_id").getAsLong();

        this.date = getDateFromString(json.get("date").getAsString());

        this.epicFactor = json.get("epicfactor").getAsInt();
    }

    private LocalDateTime getDateFromString(String timeAsString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(timeAsString, formatter);
    }

    public String getDisplayHtml() {
        return displayHtml;
    }

    public long getBeatmapId() {
        return beatmapId;
    }

    public long getBeatmapSetId() {
        return beatmapSetId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getEpicFactor() {
        return epicFactor;
    }

    @Override
    public String toString() {
        return "Event{" +
                "\ndisplayHtml='" + displayHtml + '\'' +
                ", \nbeatmapId=" + beatmapId +
                ", \nbeatmapSetId=" + beatmapSetId +
                ", \ndate=" + date +
                ", \nepicFactor=" + epicFactor +
                "\n}";
    }
}
