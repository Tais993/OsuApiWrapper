package futures;

import v1.ApiV1Handler;
import v1.entities.OsuEntityV1;

import java.util.function.Consumer;

public abstract class AbstractOsuEntityFuture<T extends OsuEntityV1> {
    private final ApiV1Handler apiV1Handler;

    AbstractOsuEntityFuture(ApiV1Handler apiV1Handler) {
        this.apiV1Handler = apiV1Handler;
    }

    public abstract T runSync() throws Exception;
    public abstract void runAsync();
    public abstract void runAsync(Consumer<? super T> consumer);
    public abstract void runAsync(Consumer<? super T> onSuccess, Consumer<? super Exception> onError);

    @Override
    public String toString() {
        return "AbstractDbFuture{" +
                "apiV1Handler=" + apiV1Handler +
                '}';
    }
}
