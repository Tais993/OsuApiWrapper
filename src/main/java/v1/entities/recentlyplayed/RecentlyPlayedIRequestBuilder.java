package v1.entities.recentlyplayed;

import v1.ApiV1Handler;
import v1.entities.IRequestBuilder;
import v1.entities.global.Mode;
import v1.entities.global.TypeV1;

@SuppressWarnings("DuplicatedCode")
public class RecentlyPlayedIRequestBuilder implements IRequestBuilder<RecentlyPlayedIRequestBuilder> {
    private String key;

    private String user;
    private TypeV1 typeV1;

    private Mode modeV1;

    int limit;

    public RecentlyPlayedIRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public RecentlyPlayedIRequestBuilder setUsername(String userName) {
        this.user = userName;
        this.typeV1 = TypeV1.USERNAME;
        return this;
    }

    public RecentlyPlayedIRequestBuilder setUserId(long userId) {
        this.user = userId + "";
        this.typeV1 = TypeV1.USER_ID;
        return this;
    }

    public RecentlyPlayedIRequestBuilder setMode(Mode modeV1) {
        this.modeV1 = modeV1;
        return this;
    }

    public RecentlyPlayedIRequestBuilder setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public String getUrl() {
        StringBuilder url = new StringBuilder(ApiV1Handler.startUrl);

        url.append("get_user_recent?k=").append(key);

        if (typeV1 != null) {
            url.append("&type=").append(typeV1);
        }

        if (user != null) {
            url.append("&u=").append(user);
        }

        if (modeV1 != null) {
            url.append("&m=").append(modeV1);
        }

        if (limit != 0) {
            url.append("&limit=").append(limit);
        }

        System.out.println(url);

        return url.toString();
    }
}
