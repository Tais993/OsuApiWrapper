package v1.entities.scores;

import v1.ApiV1Handler;
import v1.entities.RequestBuilder;
import v1.entities.global.Mod;
import v1.entities.global.Mode;
import v1.entities.global.Type;

import java.util.ArrayList;
import java.util.Arrays;

public class ScoresRequestBuilder extends RequestBuilder<ScoresRequestBuilder> {
    private String key;

    private long beatmapId;

    private String user;
    private Type type;

    private Mode mode;

    private final ArrayList<Mod> mods = new ArrayList<>();

    private int limit;

    @Override
    public ScoresRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public ScoresRequestBuilder setBeatmapId(long beatmapId) {
        this.beatmapId = beatmapId;
        return this;
    }

    public ScoresRequestBuilder setUsername(String userName) {
        this.user = userName;
        this.type = Type.USERNAME;
        return this;
    }

    public ScoresRequestBuilder setUserId(long userId) {
        this.user = userId + "";
        this.type = Type.USER_ID;
        return this;
    }

    public ScoresRequestBuilder setMode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public ScoresRequestBuilder addMods(Mod... mods) {
        this.mods.addAll(Arrays.asList(mods));
        return this;
    }

    public ScoresRequestBuilder setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public String getUrl() {
        StringBuilder url = new StringBuilder(ApiV1Handler.startUrl);

        url.append("get_scores?k=").append(key);

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

        if (!mods.isEmpty()) {
            url.append("&mods=").append(Mod.getBitwiseFromMods(mods));
        }

        if (limit != 0) {
            url.append("&limit=").append(limit);
        }

        return url.toString();
    }
}
