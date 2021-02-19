package v1.entities.scores;

import com.google.gson.JsonObject;
import v1.entities.OsuEntity;
import v1.entities.global.DateTimeHandler;
import v1.entities.global.Mod;
import v1.entities.global.ScoreRank;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static v1.entities.global.ScoreRank.getRankByString;

public class Score implements OsuEntity {
    private final long scoreId;

    private final long score;

    private final String userName;
    private final long userId;

    private final int count300;
    private final int count100;
    private final int count50;
    private final int countMiss;

    private final int maxCombo;

    private final int countKatu;
    private final int countGeki;

    private final int perfect;

    private final ArrayList<Mod> mods;

    private final LocalDateTime dateTime;

    private final ScoreRank rank;
    private final float pp;

    private final boolean replayAvailable;

    public Score(JsonObject json) {
        this.scoreId = json.get("score_id").getAsLong();

        this.score = json.get("score").getAsLong();

        this.userName = json.get("username").getAsString();
        this.userId = json.get("user_id").getAsLong();

        this.count300 = json.get("count300").getAsInt();
        this.count100 = json.get("count100").getAsInt();
        this.count50 = json.get("count50").getAsInt();
        this.countMiss = json.get("countmiss").getAsInt();

        this.maxCombo = json.get("maxcombo").getAsInt();

        this.countKatu = json.get("countkatu").getAsInt();
        this.countGeki = json.get("countgeki").getAsInt();

        this.perfect = json.get("perfect").getAsInt();

        this.mods = Mod.getByBitwise(json.get("enabled_mods").getAsInt());

        this.dateTime = DateTimeHandler.getDateTimeFromString(json.get("date").getAsString());


        this.rank = getRankByString(json.get("rank").getAsString());
        this.pp = json.get("pp").getAsFloat();

        this.replayAvailable = json.get("replay_available").getAsBoolean();
    }

    public long getScoreId() {
        return scoreId;
    }

    public long getScore() {
        return score;
    }

    public String getUserName() {
        return userName;
    }

    public long getUserId() {
        return userId;
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

    public float getPp() {
        return pp;
    }

    public boolean isReplayAvailable() {
        return replayAvailable;
    }

    @Override
    public Long getId() {
        return scoreId;
    }

    @Override
    public String getIdString() {
        return scoreId + "";
    }

    @Override
    public String toString() {
        return "Score{" +
                "\nscoreId=" + scoreId +
                ", \nscore=" + score +
                ", \nuserName='" + userName + '\'' +
                ", \nuserId=" + userId +
                ", \ncount300=" + count300 +
                ", \ncount100=" + count100 +
                ", \ncount50=" + count50 +
                ", \ncountMiss=" + countMiss +
                ", \nmaxCombo=" + maxCombo +
                ", \ncountKatu=" + countKatu +
                ", \ncountGeki=" + countGeki +
                ", \nperfect=" + perfect +
                ", \nmods=" + mods +
                ", \ndateTime=" + dateTime +
                ", \nrank=" + rank +
                ", \npp=" + pp +
                ", \nreplayAvailable=" + replayAvailable +
                "\n}";
    }
}
