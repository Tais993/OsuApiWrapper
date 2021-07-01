package v1.entities.multiplayer;

import v1.ApiV1Handler;
import v1.entities.IRequestBuilder;

public class MatchIRequestBuilder implements IRequestBuilder<MatchIRequestBuilder> {
    private String key;

    private long matchId;

    @Override
    public MatchIRequestBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public MatchIRequestBuilder setMatchId(long matchId) {
        this.matchId = matchId;
        return this;
    }

    @Override
    public String getUrl() {
        StringBuilder url = new StringBuilder(ApiV1Handler.startUrl);

        url.append("get_match?k=").append(key);

        if (matchId != 0) {
            url.append("&mp=").append(matchId);
        }

        System.out.println(url);

        return url.toString();
    }
}
