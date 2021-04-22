package v1.entities.multiplayer.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import v1.entities.OsuEntityV1;
import v1.entities.global.DateTimeHandlerV1;
import v1.entities.global.ModV1;
import v1.entities.global.ModeV1;
import v1.entities.multiplayer.MatchScoreV1;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GameV1 implements OsuEntityV1 {
    private final long gameId;

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    private final long beatmapId;

    private final ModeV1 playModeV1;
    private final String matchType;
    private final ScoringTypeV1 scoringTypeV1;
    private final TeamTypeV1 teamTypeV1;

    private final ArrayList<ModV1> modV1s;

    private final ArrayList<MatchScoreV1> matchScoreV1s = new ArrayList<>();

    public GameV1(JsonObject json) {
        this.gameId = json.get("game_id").getAsLong();

        this.startTime = DateTimeHandlerV1.getDateTimeFromString(json.get("start_time").getAsString());
        this.endTime = DateTimeHandlerV1.getDateTimeFromString(json.get("end_time").getAsString());

        this.beatmapId = json.get("beatmap_id").getAsLong();

        this.playModeV1 = ModeV1.getById(json.get("play_mode").getAsInt());

        this.matchType = json.get("match_type").getAsString();

        this.scoringTypeV1 = ScoringTypeV1.getById(json.get("scoring_type").getAsInt());
        this.teamTypeV1 = TeamTypeV1.getById(json.get("team_type").getAsInt());

        this.modV1s = ModV1.getByBitwise(json.get("mods").getAsInt());

        JsonArray scores = json.get("scores").getAsJsonArray();
        scores.forEach(jsonScore -> matchScoreV1s.add(new MatchScoreV1(jsonScore.getAsJsonObject())));
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

    public ModeV1 getPlayMode() {
        return playModeV1;
    }

    public String getMatchType() {
        return matchType;
    }

    public ScoringTypeV1 getScoringType() {
        return scoringTypeV1;
    }

    public TeamTypeV1 getTeamType() {
        return teamTypeV1;
    }

    public ArrayList<ModV1> getMods() {
        return modV1s;
    }

    public ArrayList<MatchScoreV1> getMatchScores() {
        return matchScoreV1s;
    }

    @Override
    public Long getId() {
        return gameId;
    }

    @Override
    public String getIdString() {
        return gameId + "";
    }

    @Override
    public String toString() {
        return "Game{" +
                "\ngameId=" + gameId +
                ",\n startTime=" + startTime +
                ",\n endTime=" + endTime +
                ",\n beatmapId=" + beatmapId +
                ",\n playMode=" + playModeV1 +
                ",\n matchType='" + matchType + '\'' +
                ",\n scoringType=" + scoringTypeV1 +
                ",\n teamType=" + teamTypeV1 +
                ",\n mods=" + modV1s +
                ",\n matchScores=" + matchScoreV1s +
                "\n}";
    }
}
