package v1.entities.multiplayer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import v1.entities.OsuEntity;
import v1.entities.global.DateTimeHandler;
import v1.entities.multiplayer.game.Game;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Match implements OsuEntity {
    private final long matchId;
    private final String name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    private final ArrayList<Game> games = new ArrayList<>();

    public Match(JsonObject json) {
        JsonObject match = json.get("match").getAsJsonObject();

        this.matchId = match.get("match_id").getAsLong();
        this.name = match.get("name").getAsString();
        this.startTime = DateTimeHandler.getDateTimeFromString(match.get("start_time").getAsString());
        this.endTime = DateTimeHandler.getDateTimeFromString(match.get("end_time").getAsString());

        JsonArray gamesJson = json.get("games").getAsJsonArray();
        gamesJson.forEach(gameJson -> games.add(new Game(gameJson.getAsJsonObject())));
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    @Override
    public Long getId() {
        return matchId;
    }

    @Override
    public String getIdString() {
        return matchId + "";
    }
}
