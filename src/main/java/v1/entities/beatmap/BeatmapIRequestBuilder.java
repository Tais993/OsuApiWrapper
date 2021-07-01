package v1.entities.beatmap;

import v1.ApiV1Handler;
import v1.entities.IRequestBuilder;
import v1.entities.global.ModV1;
import v1.entities.global.Mode;
import v1.entities.global.TypeV1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BeatmapIRequestBuilder implements IRequestBuilder<BeatmapIRequestBuilder> {
    private String key = null;

    private LocalDate since = null;

    private long beatmapSetId = 0;
    private long beatmapId = 0;

    private String user = null;

    private TypeV1 typeV1 = null;

    private Mode modeV1 = null;

    private boolean includesConvertedBeatmaps = false;

    private String beatmapHash = null;

    private int limit = 0;

    private final ArrayList<ModV1> modV1s = new ArrayList<>();

    @Override
    public BeatmapIRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public BeatmapIRequestBuilder since(LocalDate since) {
        this.since = since;
        return this;
    }

    public BeatmapIRequestBuilder setBeatmapSetId(long beatmapSetId) {
        this.beatmapSetId = beatmapSetId;
        return this;
    }

    public BeatmapIRequestBuilder setBeatmapId(long beatmapId) {
        this.beatmapId = beatmapId;
        return this;
    }

    public BeatmapIRequestBuilder setUsername(String user) {
        this.user = user;
        this.typeV1 = TypeV1.USERNAME;
        return this;
    }

    public BeatmapIRequestBuilder setUserId(long userId) {
        this.user = userId + "";
        this.typeV1 = TypeV1.USER_ID;
        return this;
    }

    public BeatmapIRequestBuilder setMode(Mode modeV1) {
        this.modeV1 = modeV1;
        return this;
    }

    public BeatmapIRequestBuilder includesConvertedBeatmaps(boolean includesConvertedBeatmaps) {
        this.includesConvertedBeatmaps = includesConvertedBeatmaps;
        return this;
    }

    public BeatmapIRequestBuilder setBeatmapHash(String beatmapHash) {
        this.beatmapHash = beatmapHash;
        return this;
    }

    public BeatmapIRequestBuilder setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public BeatmapIRequestBuilder addMods(ModV1... modV1s) {
        Collections.addAll(this.modV1s, modV1s);
        System.out.println(Arrays.toString(modV1s));
        System.out.println(this.modV1s);
        return this;
    }

    public BeatmapIRequestBuilder removeMod(ModV1 modV1) {
        modV1s.remove(modV1);
        return this;
    }

    public BeatmapIRequestBuilder clearMods() {
        modV1s.clear();
        return this;
    }

    public String getUrl() {
        StringBuilder url = new StringBuilder(ApiV1Handler.startUrl);

        url.append("get_beatmaps?k=").append(key);

        if (since != null) {
            url.append("&since=").append(since);
        }

        if (beatmapSetId != 0) {
            url.append("&s=").append(beatmapSetId);
        }

        if (beatmapId != 0) {
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
