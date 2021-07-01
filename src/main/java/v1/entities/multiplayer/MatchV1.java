package v1.entities.multiplayer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import v1.entities.OsuEntityV1;
import v1.entities.global.DateTimeHandlerV1;
import v1.entities.multiplayer.game.GameV1;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MatchV1 implements OsuEntityV1 {
    private final long matchId;
    private final String name;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    private final ArrayList<GameV1> gameV1s = new ArrayList<>();

    public MatchV1(JsonObject json) {
        JsonObject match = json.get("match").getAsJsonObject();

        this.matchId = match.get("match_id").getAsLong();
        this.name = match.get("name").getAsString();
        this.startTime = DateTimeHandlerV1.getDateTimeFromString(match.get("start_time").getAsString());
        this.endTime = DateTimeHandlerV1.getDateTimeFromString(match.get("end_time").getAsString());

        JsonArray gamesJson = json.get("games").getAsJsonArray();
        gamesJson.forEach(gameJson -> gameV1s.add(new GameV1(gameJson.getAsJsonObject())));
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

    public ArrayList<GameV1> getGames() {
        return gameV1s;
    }

    public ArrayList<GameV1> toGames() {
        return gameV1s;
    }

    public GameV1 toLatestGame() {
        return gameV1s.get(gameV1s.size() - 1);
    }

    @Override
    public Long getId() {
        return matchId;
    }

    @Override
    public String getIdString() {
        return matchId + "";
    }

    @Override
    public String toString() {
        return "Match{" +
                "\nmatchId=" + matchId +
                ",\n name='" + name + '\'' +
                ",\n startTime=" + startTime +
                ",\n endTime=" + endTime +
                ",\n games=" + gameV1s +
                "\n}";
    }
}
