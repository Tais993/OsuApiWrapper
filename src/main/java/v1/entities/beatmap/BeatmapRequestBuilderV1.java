package v1.entities.beatmap;

import v1.ApiV1Handler;
import v1.entities.RequestBuilderV1;
import v1.entities.global.ModV1;
import v1.entities.global.ModeV1;
import v1.entities.global.TypeV1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BeatmapRequestBuilderV1 extends RequestBuilderV1<BeatmapRequestBuilderV1> {
    private String key;

    private LocalDate since;

    private Long beatmapSetId;
    private Long beatmapId;

    private String user;

    private TypeV1 typeV1;

    private ModeV1 modeV1;

    private Boolean includesConvertedBeatmaps = false;

    private String beatmapHash;

    private int limit;

    private final ArrayList<ModV1> modV1s = new ArrayList<>();

    @Override
    public BeatmapRequestBuilderV1 setKey(String key) {
        this.key = key;
        return this;
    }

    public BeatmapRequestBuilderV1 since(LocalDate since) {
        this.since = since;
        return this;
    }

    public BeatmapRequestBuilderV1 setBeatmapSetId(Long beatmapSetId) {
        this.beatmapSetId = beatmapSetId;
        return this;
    }

    public BeatmapRequestBuilderV1 setBeatmapId(Long beatmapId) {
        this.beatmapId = beatmapId;
        return this;
    }

    public BeatmapRequestBuilderV1 setUsername(String user) {
        this.user = user;
        this.typeV1 = TypeV1.USERNAME;
        return this;
    }

    public BeatmapRequestBuilderV1 setUserId(Long userId) {
        this.user = userId + "";
        this.typeV1 = TypeV1.USER_ID;
        return this;
    }

    public BeatmapRequestBuilderV1 setMode(ModeV1 modeV1) {
        this.modeV1 = modeV1;
        return this;
    }

    public BeatmapRequestBuilderV1 includesConvertedBeatmaps(Boolean includesConvertedBeatmaps) {
        this.includesConvertedBeatmaps = includesConvertedBeatmaps;
        return this;
    }

    public BeatmapRequestBuilderV1 setBeatmapHash(String beatmapHash) {
        this.beatmapHash = beatmapHash;
        return this;
    }

    public BeatmapRequestBuilderV1 setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public BeatmapRequestBuilderV1 addMods(ModV1... modV1s) {
        Collections.addAll(this.modV1s, modV1s);
        System.out.println(Arrays.toString(modV1s));
        System.out.println(this.modV1s);
        return this;
    }

    public BeatmapRequestBuilderV1 removeMod(ModV1 modV1) {
        modV1s.remove(modV1);
        return this;
    }

    public BeatmapRequestBuilderV1 clearMods() {
        modV1s.clear();
        return this;
    }

    public String getUrl() {
        StringBuilder url = new StringBuilder(ApiV1Handler.startUrl);

        url.append("get_beatmaps?k=").append(key);

        if (since != null) {
            url.append("&since=").append(since.toString());
        }

        if (beatmapSetId != null) {
            url.append("&s=").append(beatmapSetId);
        }

        if (beatmapId != null) {
            url.append("&b=").append(beatmapId);
        }

        if (typeV1 != null) {
            url.append("&type=").append(typeV1);
        }

        if (user != null) {
            url.append("&u=").append(user);
        }

        if (modeV1 != null) {
            url.append("&m=").append(modeV1);
        }

        if (includesConvertedBeatmaps) {
            url.append("&a=").append(1);
        }

        if (beatmapHash != null) {
            url.append("&h=").append(beatmapHash);
        }

        if (limit != 0) {
            url.append("&limit=").append(limit);
        }

        System.out.println(modV1s);
        System.out.println(modV1s.isEmpty());
        if (!modV1s.isEmpty()) {
            url.append("&mods=").append(ModV1.getBitwiseFromMods(modV1s));
        }

        return url.toString();
    }
}
