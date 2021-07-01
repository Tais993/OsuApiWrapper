package v1.entities.scores;

import v1.ApiV1Handler;
import v1.entities.IRequestBuilder;
import v1.entities.global.ModV1;
import v1.entities.global.Mode;
import v1.entities.global.TypeV1;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("DuplicatedCode")
public class ScoresIRequestBuilder implements IRequestBuilder<ScoresIRequestBuilder> {
    private String key;

    private long beatmapId;

    private String user;
    private TypeV1 typeV1;

    private Mode modeV1;

    private final ArrayList<ModV1> modV1s = new ArrayList<>();

    private int limit;

    @Override
    public ScoresIRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public ScoresIRequestBuilder setBeatmapId(long beatmapId) {
        this.beatmapId = beatmapId;
        return this;
    }

    public ScoresIRequestBuilder setUsername(String userName) {
        this.user = userName;
        this.typeV1 = TypeV1.USERNAME;
        return this;
    }

    public ScoresIRequestBuilder setUserId(long userId) {
        this.user = userId + "";
        this.typeV1 = TypeV1.USER_ID;
        return this;
    }

    public ScoresIRequestBuilder setMode(Mode modeV1) {
        this.modeV1 = modeV1;
        return this;
    }

    public ScoresIRequestBuilder addMods(ModV1... modV1s) {
        this.modV1s.addAll(Arrays.asList(modV1s));
        return this;
    }

    public ScoresIRequestBuilder setLimit(int limit) {
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

        if (typeV1 != null) {
            url.append("&type=").append(typeV1);
        }

        if (user != null) {
            url.append("&u=").append(user);
        }

        if (modeV1 != null) {
            url.append("&m=").append(modeV1);
        }

        if (!modV1s.isEmpty()) {
            url.append("&mods=").append(ModV1.getBitwiseFromMods(modV1s));
        }

        if (limit != 0) {
            url.append("&limit=").append(limit);
        }

        return url.toString();
    }
}
