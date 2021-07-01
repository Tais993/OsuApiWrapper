package v1.entities.beatmap;

/**
 * @see <a href="https://osu.ppy.sh/wiki/en/Beatmap_Editor/Song_Setup#difficulty">
 *     Beatmap difficulty in the osu wiki</a>
 */
public interface IDifficulty {

    /**
     * Get the star rating
     * @return
     *  {@link Double} representing the star rating
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Beatmapping/Star_rating">
     *     Star rating in the osu wiki</a>
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Beatmap_Editor/Song_Setup#difficulty">
     *     Beatmap difficulty in the osu wiki</a>
     */
    double starRating();

    /**
     * Unknown
     * @return
     *  {@link Double} representing the aim difficulty
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Beatmap_Editor/Song_Setup#difficulty">
     *     Beatmap difficulty in the osu wiki</a>
     */
    double diffAim();

    /**
     * Unknown
     * @return
     *  {@link Double} representing the speed difficulty
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Beatmap_Editor/Song_Setup#difficulty">
     *     Beatmap difficulty in the osu wiki</a>
     */
    double diffSpeed();

    /**
     * Get the CS/Circle size
     * @return
     * {@link Double} representing the circle size
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Beatmapping/Circle_size">
     *     Circle size in the osu wiki</a>
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Beatmap_Editor/Song_Setup#difficulty">
     *     Beatmap difficulty in the osu wiki</a>
     */
    double circleSize();

    /**
     * Get the OD/Overall Difficulty
     * @return
     * {@link Double} representing the overall difficulty
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Beatmapping/Overall_difficulty">
     *     Overall difficulty in the osu wiki</a>
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Beatmap_Editor/Song_Setup#difficulty">
     *     Beatmap difficulty in the osu wiki</a>
     */
    double overallDifficulty();

    /**
     * Get the AR/Approach rate
     * @return
     * {@link Double} representing the approach rate
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Beatmapping/Approach_rate">
     *     Approach rate in the osu wiki</a>
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Beatmap_Editor/Song_Setup#difficulty">
     *     Beatmap difficulty in the osu wiki</a>
     */
    double approachRate();

    /**
     * Get the HP/Health drain
     * @return
     * {@link Double} representing the health drain
     *
     * @see <a href="https://osu.ppy.sh/wiki/sk/Beatmapping/HP_drain_rate">
     *     Health drain in the osu wiki</a>
     *
     * @see <a href="https://osu.ppy.sh/wiki/en/Beatmap_Editor/Song_Setup#difficulty">
     *     Beatmap difficulty in the osu wiki</a>
     */
    double healthDrain();
}
