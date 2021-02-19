package v1.entities.multiplayer.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import v1.entities.OsuEntity;
import v1.entities.global.DateTimeHandler;
import v1.entities.global.Mod;
import v1.entities.global.Mode;
import v1.entities.multiplayer.MatchScore;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Game implements OsuEntity {
    private final long gameId;

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    private final long beatmapId;

    private final Mode playMode;
    private final String matchType;
    private final ScoringType scoringType;
    private final TeamType teamType;

    private final ArrayList<Mod> mods;

    private final ArrayList<MatchScore> matchScores = new ArrayList<>();

    public Game(JsonObject json) {
        this.gameId = json.get("game_id").getAsLong();

        this.startTime = DateTimeHandler.getDateTimeFromString(json.get("start_time").getAsString());
        this.endTime = DateTimeHandler.getDateTimeFromString(json.get("end_time").getAsString());

        this.beatmapId = json.get("beatmap_id").getAsLong();

        this.playMode = Mode.getById(json.get("play_mode").getAsInt());

        this.matchType = json.get("match_type").getAsString();

        this.scoringType = ScoringType.getById(json.get("scoring_type").getAsInt());
        this.teamType = TeamType.getById(json.get("team_type").getAsInt());

        this.mods = Mod.getByBitwise(json.get("mods").getAsInt());

        JsonArray scores = json.get("scores").getAsJsonArray();
        scores.forEach(jsonScore -> matchScores.add(new MatchScore(jsonScore.getAsJsonObject())));
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public long getBeatmapId() {
        return beatmapId;
    }

    public Mode getPlayMode() {
        return playMode;
    }

    public String getMatchType() {
        return matchType;
    }

    public ScoringType getScoringType() {
        return scoringType;
    }

    public TeamType getTeamType() {
        return teamType;
    }

    public ArrayList<Mod> getMods() {
        return mods;
    }

    public ArrayList<MatchScore> getMatchScores() {
        return matchScores;
    }

    @Override
    public Long getId() {
        return gameId;
    }

    @Override
    public String getIdString() {
        return gameId + "";
    }
}
