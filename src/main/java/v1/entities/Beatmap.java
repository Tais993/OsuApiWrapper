package v1.entities;

import java.util.ArrayList;

public class Beatmap {
    private final boolean approved;
    private final String submitDate;
    private final String approvedDate;
    private final String lastUpdate;

    private final String artist;
    private final long beatmapId;
    private final long beatmapSetId;

    private final int bpm;

    private final String creator;
    private final long creatorId;

    private final double difficultyRating;
    private final double diffAim;
    private final double diffSpeed;
    private final double diffSize;
    private final double diffOverall;
    private final double diffApproach;
    private final double diffDrain;

    private final int hitLength;

    private final String source;
//    private final Genre genre;
//    private final Language languageId;

    private final String title;
//    private final Length totalLength;
    private final String version;
    private final String fileMd5;

//    private final Mode mode;
    private final ArrayList<String> tags;

    private final int favouriteCount;
    private final double rating;
    private final int playCount;
    private final int passCount;

    private final int countNormal;
    private final int countSlider;
    private final int countSpinner;

    private final int maxCombo;
    private final boolean storyboard;
    private final boolean video;
    private final boolean downloadUnavailable;
    private final boolean audioUnavailable;
}
