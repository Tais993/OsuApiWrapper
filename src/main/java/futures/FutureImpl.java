package futures;

import v1.ApiV1Handler;
import v1.entities.OsuEntityV1;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

public class FutureImpl<T> extends AbstractOsuEntityFuture<T> {
    private final Callable<? extends T> callable;
    private final ApiV1Handler apiV1Handler;

    public FutureImpl(final Callable<? extends T> callable, final ApiV1Handler apiV1Handler) {
        super(apiV1Handler);
        this.callable = callable;
        this.apiV1Handler = apiV1Handler;
    }

    public T runSync() throws Exception {
        return callable.call();
    }

    public void runAsync() {
        apiV1Handler.getExecutorService().submit(() -> {
            try {
                callable.call();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void runAsync(final Consumer<? super T> consumer) {
        apiV1Handler.getExecutorService().submit(() -> {
            try {
                consumer.accept(callable.call());
            } catch (final Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void runAsync(final Consumer<? super T> onSuccess, final Consumer<? super Exception> onError) {
        apiV1Handler.getExecutorService().submit(() -> {
            try {
                onSuccess.accept(callable.call());
            } catch (final Exception e) {
                onError.accept(e);
            }
        });
    }


    public <O> MappedFuture<T, O> map(final Function<? super T, ? extends O> converter) {
        return new MappedFuture<>(this, converter);
    }

    public <A, B extends List<A>> MappedToListFuture<T, A, B> mapToList(final Function<? super T, ? extends B> converter) {
        return new MappedToListFuture<>(this, converter);
    }

    public <O extends OsuEntityV1> OsuEntityFuture<T, O> mapToOsuEntity(final Function<? super T, ? extends O> converter) {
        return new OsuEntityFuture<>(this, converter);
    }

    <A extends OsuEntityV1> ApiV1Handler getApiHandler() {
        return apiV1Handler;
    }

    @Override
    public String toString() {
        return "FutureImpl{" +
                "callable=" + callable +
                ", apiV1Handler=" + apiV1Handler +
                '}';
    }
}