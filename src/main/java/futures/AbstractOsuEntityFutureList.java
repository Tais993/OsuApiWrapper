package futures;

import v1.ApiV1Handler;
import v1.entities.OsuEntityV1;

import java.util.List;

public abstract class AbstractOsuEntityFutureList<T extends OsuEntityV1, O extends List<T>> extends AbstractFutureList<T, O> {
    private final ApiV1Handler apiV1Handler;

    AbstractOsuEntityFutureList(ApiV1Handler apiV1Handler) {
        this.apiV1Handler = apiV1Handler;
    }

    @Override
    public String toString() {
        return "IDbFutureList{" +
                "apiV1Handler=" + apiV1Handler +
                '}';
    }
}
