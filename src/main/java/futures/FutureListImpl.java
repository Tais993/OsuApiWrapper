package futures;

import v1.ApiV1Handler;
import v1.entities.OsuEntityV1;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FutureListImpl<O extends OsuEntityV1, T extends List<O>> extends AbstractOsuEntityFutureList<O, T> {
    private final Callable<? extends T> callable;
    private final ApiV1Handler apiV1Handler;

    public FutureListImpl(Callable<? extends T> callable, ApiV1Handler apiV1Handler) {
        super(apiV1Handler);
        this.callable = callable;
        this.apiV1Handler = apiV1Handler;
    }

    private T getList(T list) {
        Stream<O> stream = list.stream();
        return getList(stream);
    }

    public FutureListImpl<O, T> filter(Predicate<? super O> filter) {
        setFilter(filter);
        return this;
    }

    public FutureListImpl<O, T> sort(Comparator<? super O> sorter) {
        setSorter(sorter);
        return this;
    }

    public FutureListImpl<O, T> limit(long limit) {
        setLimit(limit);
        return this;
    }

    public T runSync() throws Exception {
        return getList(callable.call());
    }


    public void runAsync() {
        apiV1Handler.getExecutorService().submit(() -> {
            try {
                getList(callable.call());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void runAsync(Consumer<? super T> onSuccess) {
        apiV1Handler.getExecutorService().submit(() -> {
            try {
                onSuccess.accept(getList(callable.call()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void runAsync(Consumer<? super T> onSuccess, Consumer<? super Exception> onError) {
        apiV1Handler.getExecutorService().submit(() -> {
            try {
                onSuccess.accept(getList(callable.call()));
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }


    public void forEach(Consumer<? super O> onSuccess) {
        try {
            getList(callable.call()).forEach(onSuccess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void forEach(Consumer<? super O> onSuccess, Consumer<? super Exception> onError) {
        try {
            getList(callable.call()).forEach(onSuccess);
        } catch (Exception e) {
            onError.accept(e);
        }
    }

    public void forEachAsync(Consumer<? super O> onSuccess) {
        runAsync(dbObjects -> {
            getList(dbObjects).forEach(onSuccess);
        });
    }

    public void forEachAsync(Consumer<? super O> onSuccess, Consumer<? super Exception> onError) {
        runAsync(dbObjects -> {
            getList(dbObjects).forEach(onSuccess);
        }, onError);
    }


    public <A> MappedListFuture<O, T, A, List<A>> map(Function<? super O, ? extends A> converter) {
        return new MappedListFuture<>(this, converter);
    }

    public <A extends OsuEntityV1> OsuEntityListFuture<O, T, A, List<A>> mapToDbObject(Function<? super O, ? extends A> converter) {
        return new OsuEntityListFuture<>(this, converter);
    }

    <A extends OsuEntityV1> ApiV1Handler getApiV1Handler() {
        return apiV1Handler;
    }

    @Override
    public String toString() {
        return "FutureListImpl{" +
                "callable=" + callable +
                ", apiV1Handler=" + apiV1Handler +
                '}';
    }
}