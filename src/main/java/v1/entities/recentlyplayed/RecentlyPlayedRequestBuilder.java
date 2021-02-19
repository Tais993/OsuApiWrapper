package v1.entities.recentlyplayed;

import v1.ApiV1Handler;
import v1.entities.RequestBuilder;
import v1.entities.global.Mode;
import v1.entities.global.Type;

public class RecentlyPlayedRequestBuilder extends RequestBuilder<RecentlyPlayedRequestBuilder> {
    private String key;

    private String user;
    private Type type;

    private Mode mode;

    int limit;

    public RecentlyPlayedRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public RecentlyPlayedRequestBuilder setUsername(String userName) {
        this.user = userName;
        this.type = Type.USERNAME;
        return this;
    }

    public RecentlyPlayedRequestBuilder setUserId(long userId) {
        this.user = userId + "";
        this.type = Type.USER_ID;
        return this;
    }

    public RecentlyPlayedRequestBuilder setMode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public RecentlyPlayedRequestBuilder setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public String getUrl() {
        StringBuilder url = new StringBuilder(ApiV1Handler.startUrl);

        url.append("get_user_recent?k=").append(key);

        if (type != null) {
            url.append("&type=").append(type);
        }

        if (user != null) {
            url.append("&u=").append(user);
        }

        if (mode != null) {
            url.append("&m=").append(mode);
        }

        if (limit != 0) {
            url.append("&limit=").append(limit);
        }

        System.out.println(url);

        return url.toString();
    }
}
