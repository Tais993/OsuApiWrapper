package futures;

import v1.entities.OsuEntityV1;

import java.util.function.Consumer;
import java.util.function.Function;

public class OsuEntityFuture<T extends OsuEntityV1, O extends OsuEntityV1> extends AbstractOsuEntityFuture<O> {
    private final FutureImpl<? extends T> asyncFuture;
    private final Function<? super T, ? extends O> converter;

    OsuEntityFuture(FutureImpl<? extends T> asyncFuture, Function<? super T, ? extends O> converter) {
        super(asyncFuture.getApiHandler());
        this.asyncFuture = asyncFuture;
        this.converter = converter;
    }

    public O runSync() throws Exception {
        return converter.apply(asyncFuture.runSync());
    }

    public void runAsync() {
        asyncFuture.runAsync();
    }

    public void runAsync(Consumer<? super O> onSuccess) {
        asyncFuture.runAsync(dbObject -> {
            onSuccess.accept(converter.apply(dbObject));
        });
    }

    public void runAsync(Consumer<? super O> onSuccess, Consumer<? super Exception> onError) {
        asyncFuture.runAsync(dbObject -> {
            onSuccess.accept(converter.apply(dbObject));
        }, onError);
    }

    @Override
    public String toString() {
        return "DbObjectFuture{" +
                "asyncFuture=" + asyncFuture +
                ", converter=" + converter +
                '}';
    }
}
