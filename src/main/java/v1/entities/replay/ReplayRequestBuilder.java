package v1.entities.replay;

import v1.ApiV1Handler;
import v1.entities.RequestBuilder;
import v1.entities.global.Mod;
import v1.entities.global.Mode;
import v1.entities.global.Type;

import java.util.ArrayList;
import java.util.Arrays;

public class ReplayRequestBuilder extends RequestBuilder<ReplayRequestBuilder> {
    private String key;

    private long beatmapId;

    private String user;
    private Type type;

    private Mode mode;

    private long scoreId;

    private final ArrayList<Mod> mods = new ArrayList<>();

    @Override
    public ReplayRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public ReplayRequestBuilder setBeatmapId(long beatmapId) {
        this.beatmapId = beatmapId;
        return this;
    }

    public ReplayRequestBuilder setUsername(String userName) {
        this.user = userName;
        this.type = Type.USERNAME;
        return this;
    }

    public ReplayRequestBuilder setUserId(long userId) {
        this.user = userId + "";
        this.type = Type.USER_ID;
        return this;
    }


    public ReplayRequestBuilder setMode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public ReplayRequestBuilder addMods(Mod... mods) {
        this.mods.addAll(Arrays.asList(mods));
        return this;
    }

    public ReplayRequestBuilder clearMods() {
        this.mods.clear();
        return this;
    }

    public ReplayRequestBuilder setScoreId(long scoreId) {
        this.scoreId = scoreId;
        return this;
    }

    @Override
    public String getUrl() {
        StringBuilder url = new StringBuilder(ApiV1Handler.startUrl);

        url.append("get_replay?k=").append(key);

        if (beatmapId != 0) {
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

        if (scoreId != 0) {
            url.append("&s=").append(scoreId);
        }

        if (!mods.isEmpty()) {
            url.append("&mods=").append(Mod.getBitwiseFromMods(mods));
        }

        System.out.println(url);

        return url.toString();
    }
}
