package v1.entities.multiplayer;

import v1.ApiV1Handler;
import v1.entities.RequestBuilderV1;

public class MatchRequestBuilderV1 extends RequestBuilderV1<MatchRequestBuilderV1> {
    private String key;

    private long matchId;

    @Override
    public MatchRequestBuilderV1 setKey(String key) {
        this.key = key;
        return this;
    }

    public MatchRequestBuilderV1 setMatchId(long matchId) {
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
