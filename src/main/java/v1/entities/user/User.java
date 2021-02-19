package v1.entities.user;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import v1.entities.OsuEntity;
import v1.entities.global.Length;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class User implements OsuEntity {
    private long userId;

    private final String userName;

    private final LocalDateTime joinTime;

    private final int count300;
    private final int count100;
    private final int count50;

    private final int playCount;

    private final long rankedScore;
    private final long totalScore;

    private final int ppRank;

    private final double level;

    private final double ppRaw;

    private final double accuracy;

    private final int countRankSS;
    private final int countRankSSH;
    private final int countRankS;
    private final int countRankSH;
    private final int countRankA;

    private final Country country;
    private final int countryRank;

    private final Length totalSecondsPlayed;

    private final Set<Event> events;

    public User(JsonObject json) {
        this.userId = json.get("user_id").getAsLong();
        this.userName = json.get("username").getAsString();
        this.joinTime = getDateTimeFromString(json.get("join_date").getAsString());

        this.count300 = json.get("count300").getAsInt();
        this.count100 = json.get("count100").getAsInt();
        this.count50 = json.get("count50").getAsInt();

        this.playCount = json.get("playcount").getAsInt();

        this.rankedScore = json.get("ranked_score").getAsLong();
        this.totalScore = json.get("total_score").getAsLong();

        this.ppRank = json.get("pp_rank").getAsInt();
        this.level = json.get("level").getAsDouble();
        this.ppRaw = json.get("pp_raw").getAsDouble();

        this.accuracy = json.get("accuracy").getAsDouble();

        this.countRankSS = json.get("count_rank_ss").getAsInt();
        this.countRankSSH = json.get("count_rank_ssh").getAsInt();
        this.countRankS = json.get("count_rank_s").getAsInt();
        this.countRankSH = json.get("count_rank_sh").getAsInt();
        this.countRankA = json.get("count_rank_a").getAsInt();

        this.country = Country.getByAbbreviation(json.get("country").getAsString());
        this.countryRank = json.get("pp_country_rank").getAsInt();

        this.totalSecondsPlayed = new Length(json.get("total_seconds_played").getAsLong());

        Set<Event> events = new HashSet<>();
        JsonArray array = json.get("events").getAsJsonArray();
        array.forEach(jsonElement -> events.add(new Event(jsonElement.getAsJsonObject())));

        this.events = events;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getJoinTime() {
        return joinTime;
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

    public int getPlayCount() {
        return playCount;
    }

    public long getRankedScore() {
        return rankedScore;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public int getPpRank() {
        return ppRank;
    }

    public double getLevel() {
        return level;
    }

    public double getPpRaw() {
        return ppRaw;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getCountRankSS() {
        return countRankSS;
    }

    public int getCountRankSSH() {
        return countRankSSH;
    }

    public int getCountRankS() {
        return countRankS;
    }

    public int getCountRankSH() {
        return countRankSH;
    }

    public int getCountRankA() {
        return countRankA;
    }

    public Country getCountry() {
        return country;
    }

    public int getCountryRank() {
        return countryRank;
    }

    public Length getTotalSecondsPlayed() {
        return totalSecondsPlayed;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public Set<Event> filterEvents(Predicate<? super Event> predicate) {
        return events.stream().filter(predicate).collect(Collectors.toSet());
    }

    public String getUserProfileImageUrl() {
        return "http://s.ppy.sh/a/" + userId;
    }

    public InputStream retrieveUserProfileImage() {
        try {
            URL url = new URL("http://s.ppy.sh/a/" + userId);
            return url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

    @Override
    public Long getId() {
        return userId;
    }

    @Override
    public String getIdString() {
        return userId + "";
    }

    private LocalDateTime getDateTimeFromString(String timeAsString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(timeAsString, formatter);
    }
}
