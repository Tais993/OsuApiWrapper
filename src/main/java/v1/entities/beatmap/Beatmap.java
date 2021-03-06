package v1.entities.beatmap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import v1.entities.OsuEntity;
import v1.entities.global.Length;
import v1.entities.global.Mode;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class Beatmap implements OsuEntity {
    private final long beatmapSetId;
    private final long beatmapId;

    private final Approved approved;

    private final Length length;
    private final int hitLength;

    private final String version;
    private final String fileMd5;

    private final double diffSize;
    private final double diffOverall;
    private final double diffApproach;
    private final double diffDrain;

    private final Mode mode;

    private final int countNormal;
    private final int countSlider;
    private final int countSpinner;

    private final LocalDateTime submitDate;
    private final LocalDateTime approvedDate;
    private final LocalDateTime lastUpdateDate;

    private final String artist;

    private final String title;

    private final String creatorName;
    private final long creatorId;

    private final double bpm;

    private final String source;
    private final ArrayList<String> tags;
    private final Genre genre;
    private final Language language;

    private final int favouriteCount;
    private final double rating;

    private final boolean storyboard;
    private final boolean video;
    private final boolean downloadUnavailable;
    private final boolean audioUnavailable;

    private final int playCount;
    private final int passCount;

    private final ArrayList<String> packs;

    private final int maxCombo;

    private final double diffAim;
    private final double diffSpeed;
    private final double difficultyRating;


    public Beatmap(JsonObject json) {
        this.beatmapSetId = json.get("beatmapset_id").getAsLong();
        this.beatmapId = json.get("beatmap_id").getAsLong();

        this.approved = Approved.fromId(json.get("approved").getAsInt());

        this.length = new Length(json.get("total_length").getAsInt());
        this.hitLength = json.get("hit_length").getAsInt();

        this.version = json.get("version").getAsString();

        this.fileMd5 = json.get("file_md5").getAsString();

        this.diffSize = json.get("diff_size").getAsDouble();
        this.diffOverall = json.get("diff_overall").getAsDouble();
        this.diffApproach = json.get("diff_approach").getAsDouble();
        this.diffDrain = json.get("diff_drain").getAsDouble();

        this.mode = Mode.getById(json.get("mode").getAsInt());

        this.countNormal = json.get("count_normal").getAsInt();
        this.countSlider = json.get("count_slider").getAsInt();
        this.countSpinner = json.get("count_spinner").getAsInt();

        this.submitDate = getDateFromString(json.get("submit_date").getAsString());
        JsonElement approvedDate = json.get("approved_date");

        this.approvedDate = approvedDate.isJsonNull() ? null : getDateFromString(approvedDate.getAsString());

        this.lastUpdateDate = getDateFromString(json.get("last_update").getAsString());

        this.artist = json.get("artist").getAsString();
        this.title = json.get("title").getAsString();

        this.creatorName = json.get("creator").getAsString();
        this.creatorId = json.get("creator_id").getAsLong();

        this.bpm = json.get("bpm").getAsDouble();

        this.source = json.get("source").getAsString();
        this.tags = new ArrayList<>(Arrays.asList(json.get("tags").getAsString().split(" ")));
        this.genre = Genre.getById(json.get("genre_id").getAsInt());
        this.language = Language.getById(json.get("language_id").getAsInt());

        this.favouriteCount = json.get("favourite_count").getAsInt();
        this.rating = json.get("rating").getAsDouble();

        this.storyboard = json.get("storyboard").getAsBoolean();
        this.video = json.get("video").getAsBoolean();
        this.downloadUnavailable = json.get("download_unavailable").getAsBoolean();
        this.audioUnavailable = json.get("audio_unavailable").getAsBoolean();

        this.playCount = json.get("playcount").getAsInt();
        this.passCount = json.get("passcount").getAsInt();

        if (json.get("packs").isJsonNull()) {
            this.packs = new ArrayList<>();
        } else {
            this.packs = new ArrayList<>(Arrays.asList(json.get("packs").getAsString().split(",")));
        }

        this.maxCombo = json.get("max_combo").getAsInt();

        this.diffAim = json.get("diff_aim").getAsDouble();
        this.diffSpeed = json.get("diff_speed").getAsDouble();

        this.difficultyRating = json.get("difficultyrating").getAsDouble();
    }

    private LocalDateTime getDateFromString(String timeAsString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(timeAsString, formatter);
    }

    public long getBeatmapSetId() {
        return beatmapSetId;
    }

    public long getBeatmapId() {
        return beatmapId;
    }

    public Approved getApproved() {
        return approved;
    }

    public Length getLength() {
        return length;
    }

    public int getHitLength() {
        return hitLength;
    }

    public String getVersion() {
        return version;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public double getDiffSize() {
        return diffSize;
    }

    public double getDiffOverall() {
        return diffOverall;
    }

    public double getDiffApproach() {
        return diffApproach;
    }

    public double getDiffDrain() {
        return diffDrain;
    }

    public Mode getMode() {
        return mode;
    }

    public int getCountNormal() {
        return countNormal;
    }

    public int getCountSlider() {
        return countSlider;
    }

    public int getCountSpinner() {
        return countSpinner;
    }

    public LocalDateTime getSubmitDate() {
        return submitDate;
    }

    public LocalDateTime getApprovedDate() {
        return approvedDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public double getBpm() {
        return bpm;
    }

    public String getSource() {
        return source;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public Genre getGenre() {
        return genre;
    }

    public Language getLanguage() {
        return language;
    }

    public int getFavouriteCount() {
        return favouriteCount;
    }

    public double getRating() {
        return rating;
    }

    public boolean isStoryboard() {
        return storyboard;
    }

    public boolean isVideo() {
        return video;
    }

    public boolean isDownloadUnavailable() {
        return downloadUnavailable;
    }

    public boolean isAudioUnavailable() {
        return audioUnavailable;
    }

    public int getPlayCount() {
        return playCount;
    }

    public int getPassCount() {
        return passCount;
    }

    public ArrayList<String> getPacks() {
        return packs;
    }

    public int getMaxCombo() {
        return maxCombo;
    }

    public double getDiffAim() {
        return diffAim;
    }

    public double getDiffSpeed() {
        return diffSpeed;
    }

    public double getDifficultyRating() {
        return difficultyRating;
    }

    public String getBeatmapCoverImageUrl() {
        return "https://assets.ppy.sh/beatmaps/" + beatmapId + "/covers/cover.jpg";
    }

    public InputStream retrieveBeatmapCoverImage() {
        try {
            URL url = new URL( "https://assets.ppy.sh/beatmaps/" + beatmapId + "/covers/cover.jpg");
            return url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

    public String getBeatmapCoverThumbnailUrl() {
        return "https://b.ppy.sh/thumb/" + beatmapId + "l.jpg";
    }

    public InputStream retrieveBeatmapCoverThumbnail() {
        try {
            URL url = new URL( "https://b.ppy.sh/thumb/" + beatmapId + "l.jpg");
            return url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NullPointerException();
    }

    public String getBeatmapLink() {
        return "https://osu.ppy.sh/beatmapsets/" + beatmapSetId + "#" + mode.getTitle().toLowerCase() + "/" + beatmapId;
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
        return "Beatmap{" +
                "\nbeatmapLink=" + getBeatmapLink() +
                "\nbeatmapSetId=" + beatmapSetId +
                ",\n beatmapId=" + beatmapId +
                ",\n approved=" + approved +
                ",\n length=" + length +
                ",\n hitLength=" + hitLength +
                ",\n version='" + version + '\'' +
                ",\n fileMd5='" + fileMd5 + '\'' +
                ",\n diffSize=" + diffSize +
                ",\n diffOverall=" + diffOverall +
                ",\n diffApproach=" + diffApproach +
                ",\n diffDrain=" + diffDrain +
                ",\n mode=" + mode +
                ",\n countNormal=" + countNormal +
                ",\n countSlider=" + countSlider +
                ",\n countSpinner=" + countSpinner +
                ",\n submitDate=" + submitDate +
                ",\n approvedDate=" + approvedDate +
                ",\n lastUpdateDate=" + lastUpdateDate +
                ",\n artist='" + artist + '\'' +
                ",\n title='" + title + '\'' +
                ",\n creatorName='" + creatorName + '\'' +
                ",\n creatorId=" + creatorId +
                ",\n bpm=" + bpm +
                ",\n source='" + source + '\'' +
                ",\n tags=" + tags +
                ",\n genre=" + genre +
                ",\n language=" + language +
                ",\n favouriteCount=" + favouriteCount +
                ",\n rating=" + rating +
                ",\n storyboard=" + storyboard +
                ",\n video=" + video +
                ",\n downloadUnavailable=" + downloadUnavailable +
                ",\n audioUnavailable=" + audioUnavailable +
                ",\n playCount=" + playCount +
                ",\n passCount=" + passCount +
                ",\n packs=" + packs +
                ",\n maxCombo=" + maxCombo +
                ",\n diffAim=" + diffAim +
                ",\n diffSpeed=" + diffSpeed +
                ",\n difficultyRating=" + difficultyRating +
                "\n}";
    }
}
