package v1.entities.user;

import v1.ApiV1Handler;
import v1.entities.RequestBuilder;
import v1.entities.global.Mode;
import v1.entities.global.Type;

public class UserRequestBuilder extends RequestBuilder<UserRequestBuilder> {
    private String key;

    private String user;
    private Mode mode;

    private Type type;

    private int eventDays;

    @Override
    public UserRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public UserRequestBuilder setUsername(String user) {
        this.user = user;
        this.type = Type.USERNAME;
        return this;
    }

    public UserRequestBuilder setUserId(long userId) {
        this.user = userId + "";
        this.type = Type.USER_ID;
        return this;
    }

    public UserRequestBuilder setMode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public UserRequestBuilder setEventDays(int eventDays) {
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

        if (type != null) {
            url.append("&type=").append(type);
        }

        if (user != null) {
            url.append("&u=").append(user);
        }

        if (mode != null) {
            url.append("&m=").append(mode);
        }

        if (eventDays != 0) {
            url.append("&event_days=").append(eventDays);
        }

        return url.toString();
    }
}
