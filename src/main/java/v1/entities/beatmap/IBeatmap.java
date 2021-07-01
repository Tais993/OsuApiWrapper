package v1.entities.beatmap;

import futures.OsuFuture;
import org.jetbrains.annotations.NotNull;
import v1.entities.global.Length;
import v1.entities.global.Mode;
import v1.entities.user.UserV1;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The beatmap interface
 * @see <a href="https://osu.ppy.sh/wiki/en/Beatmap/Difficulty">
 *    Beatmap in the osu wiki</a>
 */
public interface IBeatmap {
    /**
     * The beatmap set ID
     * @return
     *  {@link Long} with the beatmap set ID
     *
     * @see #beatmapId()
     * @see <a href="https://osu.ppy.sh/wiki/en/Beatmap#identification">
     *    Beatmap identification in the osu wiki</a>
     */
    long beatmapSetId();

    /**
     * The beatmap ID
     * @return
     *  {@link Long} with the beatmap ID
     *
     * @see #beatmapSetId()
     * @see <a href="https://osu.ppy.sh/wiki/en/Beatmap#identification">
     *    Beatmap identification in the osu wiki</a>
     */
    long beatmapId();

    /**
     * Information about it being approved
     * @return
     *  {@link Approved} contain all information about it being approved
     *
     */
    @NotNull Approved approved();

    /**
     * Get a custom object wrapping around the total length in seconds of a beatmap
     * @return
     *  {@link Length} containing the amount of seconds
     */
    @NotNull Length length();

    /**
     * Get a custom object wrapping around the total playtime in seconds of a beatmap
     * @return
     *  {@link Length} containing the amount of seconds
     */
    @NotNull Length hitLength();

    /**
     * The difficulties name
     * @return
     *  {@link String} with the difficulties name
     */
    @NotNull String version();

    /**
     * The MD5 hash of the beatmap
     * @return
     *  {@link String} with the MD5 hash of the beatmap
     */
    @NotNull String fileMd5();

    /**
     * All the difficulty relating values are stored within these
     * @return
     *  {@link IDifficulty} with the difficulty values
     */
    @NotNull IDifficulty difficulty();

    /**
     * Gets the gamemode that the Beatmap is in
     * @return
     *  {@link Mode} containing the gamemode information
     */
    @NotNull Mode mode();

    /**
     * The count of circles
     * @return
     *  {@link Integer} of the amount of circles
     *
     * @see #countSlider()
     * @see #countSpinner()
     */
    int countNormal();

    /**
     * The count of sliders
     * @return
     *  {@link Integer} of the amount of sliders
     *
     * @see #countNormal()
     * @see #countSpinner()
     */
    int countSlider();

    /**
     * The count of spinners
     * @return
     *  {@link Integer} of the amount of spinners
     *
     * @see #countNormal()
     * @see #countSlider()
     */
    int countSpinner();

    /**
     * The time the map was submitted
     * @return
     *  {@link LocalDateTime} of when the map was submitted
     *
     * @see #approvedDate()
     * @see #lastUpdateDate()
     */
    @NotNull LocalDateTime submitDate();

    /**
     * The time the map was approved
     * @return
     *  {@link LocalDateTime} of when the map was approved
     *
     * @see #submitDate()
     * @see #lastUpdateDate()
     */
    @NotNull LocalDateTime approvedDate();

    /**
     * The last time the map was updated
     * @return
     *  {@link LocalDateTime} of the latest time the map was updated
     *
     * @see #submitDate()
     * @see #approvedDate()
     */
    @NotNull LocalDateTime lastUpdateDate();

    /**
     * The song's artist name
     * @return
     *  {@link String} of the songs artist name
     */
    @NotNull String artist();

    /**
     * The song's title
     * @return
     *  {@link String} of the title
     */
    @NotNull String title();

    /**
     * The beatmaps creator name
     * @return
     *  {@link String} of the beatmaps creator name
     */
    @NotNull String creatorName();

    /**
     * The beatmaps creator ID
     * @return
     *  {@link Long} of the creator's ID
     */
    long creatorId();

    /**
     * Retrieves the creator
     * @return
     *  {@link OsuFuture} containing a {@link UserV1} of the Creator
     */
    @NotNull OsuFuture<UserV1> retrieveCreator();

    /**
     * The song's BPM
     * @return
     *  {@link Double} of the BPM
     */
    double bpm();

    // TODO finish docs
    /**
     * The song's source
     * @return
     *  {@link String} of the source
     */
    @NotNull String source();

    /**
     * A unmodifiable list of all the possible tags
     * @return
     *  {@link List} of {@link String strings} containing all the tags
     */
    @NotNull List<String> tags();

    /**
     * tHE
     * @return
     */
    @NotNull Genre genre();
    @NotNull Language language();
    int favouriteCount();
    double rating();
    boolean hasStoryboard();
    boolean hasVideo();
    boolean hasDownloadAvailable();
    boolean hasAudioAvailable();
    int playCount();
    int passCount();
    @NotNull List<String> packs();

    @NotNull String getBeatmapCoverImageUrl();
    @NotNull InputStream retrieveBeatmapCoverImage() throws IOException;

    @NotNull String getBeatmapCoverThumbnailUrl();
    @NotNull InputStream retrieveBeatmapCoverThumbnail() throws IOException;

    @NotNull String getBeatmapLink();
}
