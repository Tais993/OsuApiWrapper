package futures;

import v1.entities.OsuEntityV1;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MappedListFuture<O extends OsuEntityV1, T extends List<O>, A, B extends List<A>> extends AbstractFutureList<A, B> {
    private final FutureListImpl<O, ? extends T> asyncFuture;
    private final Function<? super O, ? extends A> converter;
    MappedListFuture(FutureListImpl<O, ? extends T> asyncFuture, Function<? super O, ? extends A> converter) {
        this.asyncFuture = asyncFuture;
        this.converter = converter;
    }

    private B getList(T list) {
        Stream<A> stream = list.stream().map(converter);
        return getList(stream);
    }

    public MappedListFuture<O, T, A, B> filter(Predicate<? super A> filter) {
        setFilter(filter);
        return this;
    }

    public MappedListFuture<O, T, A, B> sort(Comparator<? super A> sorter) {
        setSorter(sorter);
        return this;
    }

    public MappedListFuture<O, T, A, B> limit(long limit) {
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
        asyncFuture.runAsync(dbObject -> {
            onSuccess.accept(getList(dbObject));
        });
    }

    public void runAsync(Consumer<? super B> onSuccess, Consumer<? super Exception> onError) {
        asyncFuture.runAsync(dbObject -> {
            onSuccess.accept(getList(dbObject));
        }, onError);
    }


    public void forEach(Consumer<? super A> onSuccess) {
        try {
            getList(asyncFuture.runSync()).forEach(onSuccess);
        } catch (Exception ignored) {}
    }

    public void forEach(Consumer<? super A> onSuccess, Consumer<? super Exception> onError) {
        try {
            getList(asyncFuture.runSync()).forEach(onSuccess);
        } catch (Exception e) {
            onError.accept(e);
        }
    }

    public void forEachAsync(Consumer<? super A> onSuccess) {
        asyncFuture.runAsync(dbObjects -> {
            getList(dbObjects).forEach(onSuccess);
        });
    }

    public void forEachAsync(Consumer<? super A> onSuccess, Consumer<? super Exception> onError) {
        asyncFuture.runAsync(dbObjects -> {
            getList(dbObjects).forEach(onSuccess);
        }, onError);
    }

    @Override
    public String toString() {
        return "MappedListFuture{" +
                "asyncFuture=" + asyncFuture +
                ", converter=" + converter +
                '}';
    }
}
