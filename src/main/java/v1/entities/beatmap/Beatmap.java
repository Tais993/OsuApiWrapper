package v1.entities.beatmap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import futures.OsuFuture;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import v1.entities.global.Length;
import v1.entities.global.Mode;
import v1.entities.user.UserV1;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public record Beatmap(
        long beatmapSetId,
        long beatmapId,

        Approved approved,

        Length length,
        Length hitLength,

        String version,
        String fileMd5,

        IDifficulty difficulty,

        Mode mode,

        int countNormal,
        int countSlider,
        int countSpinner,

        LocalDateTime submitDate,
        LocalDateTime approvedDate,
        LocalDateTime lastUpdateDate,

        String artist,

        String title,

        String creatorName,
        long creatorId,

        double bpm,

        String source,
        List<String> tags,
        Genre genre,
        Language language,

        int favouriteCount,
        double rating,

        boolean hasStoryboard,
        boolean hasVideo,
        boolean hasDownloadAvailable,
        boolean hasAudioAvailable,

        int playCount,
        int passCount,

        List<String> packs,

        int maxCombo) implements IBeatmap {
    private static final Logger logger = LoggerFactory.getLogger(Beatmap.class);

    public static Beatmap fromJson(final JsonObject json) {
        final long beatmapSetId = json.get("beatmapset_id").getAsLong();
        final long beatmapId = json.get("beatmap_id").getAsLong();

        final Approved approved = Approved.fromId(json.get("approved").getAsInt());

        final Length lengthV1 = new Length(json.get("total_length").getAsInt());
        final Length hitLength = new Length(json.get("hit_length").getAsInt());

        final String version = json.get("version").getAsString();

        final String fileMd5 = json.get("file_md5").getAsString();

        final IDifficulty difficulty = DifficultyImpl.ofJson(json);

        final Mode modeV1 = Mode.getById(json.get("mode").getAsInt());

        final int countNormal = json.get("count_normal").getAsInt();
        final int countSlider = json.get("count_slider").getAsInt();
        final int countSpinner = json.get("count_spinner").getAsInt();

        final LocalDateTime submitDate = getDateFromString(json.get("submit_date").getAsString());
        final JsonElement approvedDateJson = json.get("approved_date");

        final LocalDateTime approvedDate = approvedDateJson.isJsonNull() ? null : getDateFromString(approvedDateJson.getAsString());

        final LocalDateTime lastUpdateDate = getDateFromString(json.get("last_update").getAsString());

        final String artist = json.get("artist").getAsString();
        final String title = json.get("title").getAsString();

        final String creatorName = json.get("creator").getAsString();
        final long creatorId = json.get("creator_id").getAsLong();

        final double bpm = json.get("bpm").getAsDouble();

        final String source = json.get("source").getAsString();
        final List<String> tags = new ArrayList<>(Arrays.asList(json.get("tags").getAsString().split(" ")));
        final Genre genre = Genre.getById(json.get("genre_id").getAsInt());
        final Language language = Language.getById(json.get("language_id").getAsInt());

        final int favouriteCount = json.get("favourite_count").getAsInt();
        final double rating = json.get("rating").getAsDouble();

        final boolean hasStoryboard = json.get("storyboard").getAsBoolean();
        final boolean hasVideo = json.get("video").getAsBoolean();
        final boolean hasDownloadAvailable = !json.get("download_unavailable").getAsBoolean();
        final boolean hasAudioAvailable = !json.get("audio_unavailable").getAsBoolean();

        final int playCount = json.get("playcount").getAsInt();
        final int passCount = json.get("passcount").getAsInt();

        final List<String> packs;

        if (json.get("packs").isJsonNull()) {
            packs = new ArrayList<>();
        } else {
            packs = new ArrayList<>(Arrays.asList(json.get("packs").getAsString().split(",")));
        }

        final int maxCombo = json.get("max_combo").getAsInt();

        return new Beatmap(beatmapSetId, beatmapId, approved, lengthV1, hitLength, version, fileMd5, difficulty, modeV1, countNormal, countSlider, countSpinner, submitDate, approvedDate, lastUpdateDate, artist, title, creatorName, creatorId, bpm, source, tags, genre, language, favouriteCount, rating, hasStoryboard, hasVideo, hasDownloadAvailable, hasAudioAvailable, playCount, passCount, packs, maxCombo);
    }

    @NotNull
    private static LocalDateTime getDateFromString(final CharSequence timeAsString) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(timeAsString, formatter);
    }

    @NotNull
    public List<String> tags() {
        return Collections.unmodifiableList(tags);
    }

    // TODO
    @Override
    public @NotNull OsuFuture<UserV1> retrieveCreator() {
        return null;
    }

    @NotNull
    public String getBeatmapCoverImageUrl() {
        return "https://assets.ppy.sh/beatmaps/" + beatmapSetId + "/covers/cover.jpg";
    }

    @NotNull
    public InputStream retrieveBeatmapCoverImage() throws IOException {
        final URL url = new URL( "https://assets.ppy.sh/beatmaps/" + beatmapSetId + "/covers/cover.jpg");
        return url.openStream();
    }

    @NotNull
    public String getBeatmapCoverThumbnailUrl() {
        return "https://b.ppy.sh/thumb/" + beatmapSetId + "l.jpg";
    }

    @NotNull
    public InputStream retrieveBeatmapCoverThumbnail() throws IOException {
        final URL url = new URL( "https://b.ppy.sh/thumb/" + beatmapSetId + "l.jpg");
        return url.openStream();
    }

    @NotNull
    public String getBeatmapLink() {
        return "https://osu.ppy.sh/beatmapsets/" + beatmapSetId() + '#' + mode.getTitle().toLowerCase(Locale.ROOT) + '/' + beatmapId();
    }
}
