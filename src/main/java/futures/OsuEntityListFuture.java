package futures;


import v1.ApiV1Handler;
import v1.entities.OsuEntityV1;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class OsuEntityListFuture<O extends OsuEntityV1, T extends List<O>, A extends OsuEntityV1, B extends List<A>> extends AbstractOsuEntityFutureList<A, B> {
    private final FutureListImpl<O, ? extends T> asyncFuture;
    private final ApiV1Handler apiV1Handler;
    private final Function<? super O, ? extends A> converter;

    OsuEntityListFuture(FutureListImpl<O, ? extends T> asyncFuture, Function<? super O, ? extends A> converter) {
        super(asyncFuture.getApiV1Handler());
        this.asyncFuture = asyncFuture;
        this.converter = converter;
        apiV1Handler = asyncFuture.getApiV1Handler();
    }

    private B getList(T list) {
        return getList(list.stream().map(converter));
    }

    public OsuEntityListFuture<O, T, A, B> filter(Predicate<? super A> filter) {
        setFilter(filter);
        return this;
    }

    public OsuEntityListFuture<O, T, A, B> sort(Comparator<? super A> sorter) {
        setSorter(sorter);
        return this;
    }

    public OsuEntityListFuture<O, T, A, B> limit(long limit) {
        setLimit(limit);
        return this;
    }


    public B runSync() throws Exception {
        return getList(asyncFuture.runSync());
    }

    public void runAsync() {
        asyncFuture.runAsync(this::getList);
    }

    public void runAsync(Consumer<? super B> onSuccess) {
        asyncFuture.runAsync(OsuEntityV1 -> {
            onSuccess.accept(getList(OsuEntityV1));
        });
    }

    public void runAsync(Consumer<? super B> onSuccess, Consumer<? super Exception> onError) {
        asyncFuture.runAsync(OsuEntityV1 -> {
            onSuccess.accept(getList(OsuEntityV1));
        }, onError);
    }


    public void forEach(Consumer<? super A> onSuccess) {
        try {
            getList(asyncFuture.runSync()).forEach(onSuccess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void forEach(Consumer<? super A> onSuccess, Consumer<? super Exception> onError) {
        try {
            getList(asyncFuture.runSync()).forEach(onSuccess);
        } catch (Exception e) {
            onError.accept(e);
        }
    }

    public void forEachAsync(Consumer<? super A> onSuccess) {
        asyncFuture.runAsync(OsuEntityV1s -> {
            OsuEntityV1s.stream().map(converter).forEach(onSuccess);
        });
    }

    public void forEachAsync(Consumer<? super A> onSuccess, Consumer<? super Exception> onError) {
        asyncFuture.runAsync(OsuEntityV1s -> {
            OsuEntityV1s.stream().map(converter).forEach(onSuccess);
        }, onError);
    }

    @Override
    public String toString() {
        return "OsuEntityV1ListFuture{" +
                "asyncFuture=" + asyncFuture +
                ", databaseHandler=" + apiV1Handler +
                ", converter=" + converter +
                '}';
    }
}
