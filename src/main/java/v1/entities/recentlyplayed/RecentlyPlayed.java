package v1.entities.recentlyplayed;

import com.google.gson.JsonObject;
import v1.entities.OsuEntity;
import v1.entities.global.DateTimeHandler;
import v1.entities.global.Mod;
import v1.entities.global.ScoreRank;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static v1.entities.global.ScoreRank.getRankByString;

public class RecentlyPlayed implements OsuEntity {
    private final long beatmapId;

    private final long score;
    private final int maxCombo;

    private final int count300;
    private final int count100;
    private final int count50;
    private final int countMiss;

    private final int countKatu;
    private final int countGeki;

    private final int perfect;

    private final ArrayList<Mod> mods;

    private final long userId;

    private final LocalDateTime dateTime;

    private final ScoreRank rank;

    public RecentlyPlayed(JsonObject json) {
        this.beatmapId = json.get("beatmap_id").getAsInt();

        this.score = json.get("score").getAsLong();
        this.maxCombo = json.get("maxcombo").getAsInt();

        this.count300 = json.get("count300").getAsInt();
        this.count100 = json.get("count100").getAsInt();
        this.count50 = json.get("count50").getAsInt();
        this.countMiss = json.get("countmiss").getAsInt();

        this.countKatu = json.get("countkatu").getAsInt();
        this.countGeki = json.get("countgeki").getAsInt();
        this.perfect = json.get("perfect").getAsInt();

        this.mods = Mod.getByBitwise(json.get("enabled_mods").getAsInt());

        this.userId = json.get("user_id").getAsLong();

        this.dateTime = DateTimeHandler.getDateTimeFromString(json.get("date").getAsString());

        this.rank = getRankByString(json.get("rank").getAsString());
    }

    public long getScore() {
        return score;
    }

    public int getCount300() {
        return count300;
    }

    public int getCount100() {
        return count100;
    }

    public int getCount50() {
        return count50;
    }

    public int getCountMiss() {
        return countMiss;
    }

    public int getMaxCombo() {
        return maxCombo;
    }

    public int getCountKatu() {
        return countKatu;
    }

    public int getCountGeki() {
        return countGeki;
    }

    public int getPerfect() {
        return perfect;
    }

    public ArrayList<Mod> getMods() {
        return mods;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public ScoreRank getRank() {
        return rank;
    }

    @Override
    public Long getId() {
        return beatmapId;
    }

    @Override
    public String getIdString() {
        return beatmapId + "";
    }

    @Override
    public String toString() {
        return "BestPerformance{" +
                "\nbeatmapId=" + beatmapId +
                ",\n score=" + score +
                ",\n maxCombo=" + maxCombo +
                ",\n count300=" + count300 +
                ",\n count100=" + count100 +
                ",\n count50=" + count50 +
                ",\n countMiss=" + countMiss +
                ",\n countKatu=" + countKatu +
                ",\n countGeki=" + countGeki +
                ",\n perfect=" + perfect +
                ",\n mods=" + mods +
                ",\n userId=" + userId +
                ",\n dateTime=" + dateTime +
                ",\n rank=" + rank +
                "\n}";
    }
}
