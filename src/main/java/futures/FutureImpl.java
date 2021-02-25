package futures;

import v1.entities.OsuEntity;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;
import java.util.function.Function;

public class FutureImpl<T extends OsuEntity> {
    private final Callable<T> callable;
    private final ExecutorService executorService;

    public FutureImpl(Callable<T> callable, ExecutorService executorService) {
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

    public <O> ConvertedFuture<T, O> convert(Function<T, ? extends O> converter) {
        return new ConvertedFuture<>(this, converter);
    }

    public <O extends OsuEntity> OsuEntityFuture<T, O> convertToOsuEntity(Function<T, ? extends O> converter) {
        return new OsuEntityFuture<>(this, converter);
    }
}
