package v1.entities.multiplayer;

import com.google.gson.JsonObject;
import v1.entities.OsuEntityV1;
import v1.entities.global.ModV1;

import java.util.ArrayList;

public class MatchScoreV1 implements OsuEntityV1 {
    private final int slot;
    private final TeamV1 teamV1;

    private final long userId;

    private final long score;

    private final int maxCombo;


    private final int count300;
    private final int count100;
    private final int count50;
    private final int countMiss;

    private final int countKatu;
    private final int countGeki;


    private final boolean hasPassed;
    private final int perfect;

    private final ArrayList<ModV1> modV1s;

    public MatchScoreV1(JsonObject json) {
        this.slot = json.get("slot").getAsInt();
        this.teamV1 = TeamV1.getById(json.get("team").getAsInt());

        this.score = json.get("score").getAsLong();
        this.userId = json.get("user_id").getAsLong();

        this.count300 = json.get("count300").getAsInt();
        this.count100 = json.get("count100").getAsInt();
        this.count50 = json.get("count50").getAsInt();
        this.countMiss = json.get("countmiss").getAsInt();

        this.maxCombo = json.get("maxcombo").getAsInt();

        this.countKatu = json.get("countkatu").getAsInt();
        this.countGeki = json.get("countgeki").getAsInt();

        this.perfect = json.get("perfect").getAsInt();

        this.modV1s = ModV1.getByBitwise(json.get("enabled_mods").getAsInt());

        this.hasPassed = json.get("pass").getAsBoolean();
    }

    public int getSlot() {
        return slot;
    }

    public TeamV1 getTeam() {
        return teamV1;
    }

    public long getScore() {
        return score;
    }

    public int getMaxCombo() {
        return maxCombo;
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

    public int getCountKatu() {
        return countKatu;
    }

    public int getCountGeki() {
        return countGeki;
    }

    public boolean isHasPassed() {
        return hasPassed;
    }

    public int getPerfect() {
        return perfect;
    }

    public ArrayList<ModV1> getMods() {
        return modV1s;
    }

    @Override
    public Long getId() {
        return userId;
    }

    @Override
    public String getIdString() {
        return userId + "";
    }

    @Override
    public String toString() {
        return "MatchScore{" +
                "\nslot=" + slot +
                ",\n team=" + teamV1 +
                ",\n userId=" + userId +
                ",\n score=" + score +
                ",\n maxCombo=" + maxCombo +
                ",\n count300=" + count300 +
                ",\n count100=" + count100 +
                ",\n count50=" + count50 +
                ",\n countMiss=" + countMiss +
                ",\n countKatu=" + countKatu +
                ",\n countGeki=" + countGeki +
                ",\n hasPassed=" + hasPassed +
                ",\n perfect=" + perfect +
                ",\n mods=" + modV1s +
                "\n}";
    }
}
