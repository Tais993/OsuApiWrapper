package v1.entities.bestperformance;

import v1.ApiV1Handler;
import v1.entities.RequestBuilder;
import v1.entities.global.Mode;
import v1.entities.global.Type;

public class BestPerformanceRequestBuilder extends RequestBuilder<BestPerformanceRequestBuilder> {
    private String key;

    private String user;
    private Type type;

    private Mode mode;

    int limit;

    @Override
    public BestPerformanceRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public BestPerformanceRequestBuilder setUsername(String userName) {
        this.user = userName;
        this.type = Type.USERNAME;
        return this;
    }

    public BestPerformanceRequestBuilder setUserId(long userId) {
        this.user = userId + "";
        this.type = Type.USER_ID;
        return this;
    }

    public BestPerformanceRequestBuilder setMode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public BestPerformanceRequestBuilder setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public String getUrl() {
        StringBuilder url = new StringBuilder(ApiV1Handler.startUrl);

        url.append("get_user_best?k=").append(key);

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
