package futures;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractFutureList<A, B extends List<? extends A>> {
    private Predicate<? super A> filter = null;
    private Comparator<? super A> sorter = null;
    private long limit = 0L;

    B getList(Stream<? extends A> stream) {

        Stream<? extends A> localStream = stream;
        if (null != filter) {
            localStream = localStream.filter(filter);
        }

        if (null != sorter) {
            localStream = localStream.sorted(sorter);
        }

        if (0L < limit) {
            localStream = localStream.limit(limit);
        }

        //noinspection unchecked
        return (B) localStream.collect(Collectors.toList());
    }

    void setFilter(Predicate<? super A> filter) {
        this.filter = filter;
    }

    void setSorter(Comparator<? super A> sorter) {
        this.sorter = sorter;
    }

    void setLimit(long limit) {
        this.limit = limit;
    }

    public abstract B runSync() throws Exception;
    public abstract void runAsync();
    public abstract void runAsync(Consumer<? super B> consumer);
    public abstract void runAsync(Consumer<? super B> onSuccess, Consumer<? super Exception> onError);

    public abstract void forEach(Consumer<? super A> onSuccess);
    public abstract void forEach(Consumer<? super A> onSuccess, Consumer<? super Exception> onError);
    public abstract void forEachAsync(Consumer<? super A> onSuccess);
    public abstract void forEachAsync(Consumer<? super A> onSuccess, Consumer<? super Exception> onError);

    @Override
    public String toString() {
        return "AbstractFutureList{" +
                "filter=" + filter +
                ", sorter=" + sorter +
                ", limit=" + limit +
                '}';
    }
}
