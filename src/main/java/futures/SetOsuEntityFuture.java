package futures;

import v1.entities.OsuEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public class SetOsuEntityFuture<A extends OsuEntity, T extends Set<A>, O extends OsuEntity> {
    private final SetFutureImpl<A, T> asyncFuture;
    private final Function<A, ? extends O> converter;

    public SetOsuEntityFuture(SetFutureImpl<A, T> asyncFuture, Function<A, ? extends O> converter) {
        this.asyncFuture = asyncFuture;
        this.converter = converter;
    }

    public CompletableFuture<Void> submit() {
        return asyncFuture.submit();
    }

    public void runAsync(Consumer<Set<O>> consumer) {
        asyncFuture.runAsync(retrievedSet -> {
            Set<O> dbObjectSet = new HashSet<>();
            retrievedSet.forEach(dbObject -> dbObjectSet.add(converter.apply(dbObject)));

            consumer.accept(dbObjectSet);
        });
    }

    public Set<O> runSync() {
        try {
            Set<O> dbObjectSet = new HashSet<>();
            asyncFuture.runSync().forEach(dbObject -> dbObjectSet.add(converter.apply(dbObject)));

            return dbObjectSet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void forEachAsync(Consumer<O> consumer) {
        asyncFuture.runAsync(retrievedSet -> {
            Set<O> dbObjectSet = new HashSet<>();
            retrievedSet.forEach(dbObject -> dbObjectSet.add(converter.apply(dbObject)));

            dbObjectSet.forEach(consumer);
        });
    }

    public void forEachSync(Consumer<O> consumer) {
            Set<O> dbObjectSet = new HashSet<>();
            asyncFuture.runSync().forEach(dbObject -> dbObjectSet.add(converter.apply(dbObject)));

            dbObjectSet.forEach(consumer);
    }
}
