package osu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class V2Settings {
    private ArrayBlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(100);
    private ExecutorService executorService = new ThreadPoolExecutor(1, 10, 100L, TimeUnit.SECONDS, blockingQueue);

    public V2Settings() {
    }
}
