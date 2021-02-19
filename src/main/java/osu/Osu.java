package osu;

import v1.ApiV1Handler;

public class Osu {
    private final String token;
    private final ApiV1Handler apiV1Handler;

    public Osu(String token) {
        this.token = token;
        this.apiV1Handler = new ApiV1Handler(token);
    }

    public ApiV1Handler getV1() {
        return apiV1Handler;
    }
}
