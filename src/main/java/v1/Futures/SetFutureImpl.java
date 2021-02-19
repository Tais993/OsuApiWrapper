package v1.Futures;

import v1.entities.OsuEntity;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;
import java.util.function.Function;

public class SetFutureImpl<O extends OsuEntity, T extends Set<O>> {
    private final Callable<T> callable;
    private final ExecutorService executorService;

    public SetFutureImpl(Callable<T> callable, ExecutorService executorService) {
        this.callable = callable;
        this.executorService = executorService;
    }

    public CompletableFuture<Void> submit() {
        FutureTask<T> task = new FutureTask<>(callable);
        return CompletableFuture.runAsync(task, executorService);
    }

    public void runAsync() {
        executorService.submit(() -> {
            try {
                callable.call();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    public void runAsync(Consumer<T> consumer) {
        executorService.submit(() -> {
            try {
                consumer.accept(callable.call());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void runAsync(Consumer<T> onSuccess, Consumer<Exception> onError) {
        executorService.submit(() -> {
            try {
                onSuccess.accept(callable.call());
            } catch (Exception e) {
                onError.accept(e);
            }
        });
    }

    public T runSync() {
        try {
            return callable.call();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public <O> ConvertedFuture<T, O> convert(Function<T, ? extends O> converter) {
//        return new ConvertedFuture<>(this, converter);
//    }

    public <A extends OsuEntity> SetOsuEntityFuture<O, T, O> convertToOsuEntity(Function<O, ? extends O> converter) {
        return new SetOsuEntityFuture<>(this, converter);
    }

    public void forEachAsync(Consumer<O> consumer) {
        runAsync(retrievedSet -> retrievedSet.forEach(consumer));
    }

    public void forEachSync(Consumer<O> consumer) {
        runSync().forEach(consumer);
    }
}
