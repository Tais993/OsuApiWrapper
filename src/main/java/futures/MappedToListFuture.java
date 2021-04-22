package futures;

import v1.entities.OsuEntityV1;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MappedToListFuture<O extends OsuEntityV1, A, B extends List<A>> extends AbstractFutureList<A, B> {
    private final FutureImpl<? extends O> asyncFuture;
    private final Function<? super O, ? extends B> converter;

    MappedToListFuture(FutureImpl<? extends O> asyncFuture, Function<? super O, ? extends B> converter) {
        this.asyncFuture = asyncFuture;
        this.converter = converter;
    }

    private B getList(O dbObject) {
        Stream<A> stream = converter.apply(dbObject).stream();
        return getList(stream);
    }

    public MappedToListFuture<O, A, B> filter(Predicate<? super A> filter) {
        setFilter(filter);
        return this;
    }

    public MappedToListFuture<O, A, B> sort(Comparator<? super A> sorter) {
        setSorter(sorter);
        return this;
    }

    public MappedToListFuture<O, A, B> limit(long limit) {
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
        return "MappedToListFuture{" +
                "asyncFuture=" + asyncFuture +
                ", converter=" + converter +
                '}';
    }
}
