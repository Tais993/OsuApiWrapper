package osu;

import v1.ApiV1Handler;
import v2.ApiV2Handler;

public class Osu {
    private final String keyV1;
    private final String keyV2;

    private final boolean isUsingV1;
    private final boolean isUsingV2;

    private ApiV1Handler apiV1Handler;
    private ApiV2Handler apiV2Handler;

    public Osu(OsuSettings osuSettings) {
        this.keyV1 = osuSettings.getKeyV1();
        this.keyV2 = osuSettings.getKeyV2();

        this.isUsingV1 = osuSettings.isUsingV1();
        this.isUsingV2 = osuSettings.isUsingV2();

        if (isUsingV1) {
            this.apiV1Handler = new ApiV1Handler(osuSettings);
        }

        if (isUsingV2) {
            this.apiV2Handler = new ApiV2Handler(osuSettings);
        }
    }

    public ApiV1Handler getV1() {
        if (isUsingV1) {
            return apiV1Handler;
        } else {
            throw new IllegalStateException("V1 has been disabled, please set a key on the OsuSettingsBuilder first!");
        }
    }

    public ApiV2Handler getV2() {
        if (isUsingV2) {
            return apiV2Handler;
        } else {
            throw new IllegalStateException("V2 has been disabled, please set a key on the OsuSettingsBuilder first!");
        }
    }
}
