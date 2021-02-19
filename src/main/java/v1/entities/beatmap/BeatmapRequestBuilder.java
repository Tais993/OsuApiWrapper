package v1.entities.beatmap;

import v1.ApiV1Handler;
import v1.entities.RequestBuilder;
import v1.entities.global.Mod;
import v1.entities.global.Mode;
import v1.entities.global.Type;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BeatmapRequestBuilder extends RequestBuilder<BeatmapRequestBuilder> {
    private String key;

    private LocalDate since;

    private Long beatmapSetId;
    private Long beatmapId;

    private String user;

    private Type type;

    private Mode mode;

    private Boolean includesConvertedBeatmaps = false;

    private String beatmapHash;

    private int limit;

    private final ArrayList<Mod> mods = new ArrayList<>();

    @Override
    public BeatmapRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public BeatmapRequestBuilder since(LocalDate since) {
        this.since = since;
        return this;
    }

    public BeatmapRequestBuilder setBeatmapSetId(Long beatmapSetId) {
        this.beatmapSetId = beatmapSetId;
        return this;
    }

    public BeatmapRequestBuilder setBeatmapId(Long beatmapId) {
        this.beatmapId = beatmapId;
        return this;
    }

    public BeatmapRequestBuilder setUsername(String user) {
        this.user = user;
        this.type = Type.USERNAME;
        return this;
    }

    public BeatmapRequestBuilder setUserId(Long userId) {
        this.user = userId + "";
        this.type = Type.USER_ID;
        return this;
    }

    public BeatmapRequestBuilder setMode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public BeatmapRequestBuilder includesConvertedBeatmaps(Boolean includesConvertedBeatmaps) {
        this.includesConvertedBeatmaps = includesConvertedBeatmaps;
        return this;
    }

    public BeatmapRequestBuilder setBeatmapHash(String beatmapHash) {
        this.beatmapHash = beatmapHash;
        return this;
    }

    public BeatmapRequestBuilder setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public BeatmapRequestBuilder addMods(Mod... mods) {
        Collections.addAll(this.mods, mods);
        System.out.println(Arrays.toString(mods));
        System.out.println(this.mods);
        return this;
    }

    public BeatmapRequestBuilder removeMod(Mod mod) {
        mods.remove(mod);
        return this;
    }

    public BeatmapRequestBuilder clearMods() {
        mods.clear();
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

        if (type != null) {
            url.append("&type=").append(type);
        }

        if (user != null) {
            url.append("&u=").append(user);
        }

        if (mode != null) {
            url.append("&m=").append(mode);
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

        System.out.println(mods);
        System.out.println(mods.isEmpty());
        if (!mods.isEmpty()) {
            url.append("&mods=").append(Mod.getBitwiseFromMods(mods));
        }

        return url.toString();
    }
}
