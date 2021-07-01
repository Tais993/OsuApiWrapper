package v1.entities.user;

import v1.ApiV1Handler;
import v1.entities.IRequestBuilder;
import v1.entities.global.Mode;
import v1.entities.global.TypeV1;

public class UserIRequestBuilder implements IRequestBuilder<UserIRequestBuilder> {
    private String key;

    private String user;
    private Mode modeV1;

    private TypeV1 typeV1;

    private int eventDays;

    @Override
    public UserIRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public UserIRequestBuilder setUsername(String user) {
        this.user = user;
        this.typeV1 = TypeV1.USERNAME;
        return this;
    }

    public UserIRequestBuilder setUserId(long userId) {
        this.user = userId + "";
        this.typeV1 = TypeV1.USER_ID;
        return this;
    }

    public UserIRequestBuilder setMode(Mode modeV1) {
        this.modeV1 = modeV1;
        return this;
    }

    public UserIRequestBuilder setEventDays(int eventDays) {
        if (eventDays >= 1 && eventDays <= 31) {
            this.eventDays = eventDays;
            return this;
        } else {
            throw new IllegalStateException("eventDays should be below or equal to 31 and above or equal to 1");
        }
    }

    @Override
    public String getUrl() {
        StringBuilder url = new StringBuilder(ApiV1Handler.startUrl);

        url.append("get_user?k=").append(key);

        if (typeV1 != null) {
            url.append("&type=").append(typeV1);
        }

        if (user != null) {
            url.append("&u=").append(user);
        }

        if (modeV1 != null) {
            url.append("&m=").append(modeV1);
        }

        if (eventDays != 0) {
            url.append("&event_days=").append(eventDays);
        }

        return url.toString();
    }
}
