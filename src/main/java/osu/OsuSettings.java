package osu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OsuSettings {
    private final String keyV1;
    private final String keyV2;

    private final ArrayBlockingQueue<Runnable> v1BlockingQueue;
    private ArrayBlockingQueue<Runnable> v2BlockingQueue;
    private final ExecutorService v1ExecutorService;
    private ExecutorService v2ExecutorService;

    private final boolean useSeparateQueues;

    public OsuSettings(OsuSettingsBuilder osuSB) {
        keyV1 = osuSB.getKeyV1();
        keyV2 = osuSB.getKeyV2();

        if (osuSB.isSeparateQueues()) {
            useSeparateQueues = true;
            v1BlockingQueue = new ArrayBlockingQueue<>(osuSB.getQueueCapacity());
            v1ExecutorService = new ThreadPoolExecutor(osuSB.getThreadCount(), osuSB.getMaxThreadCount(), 100L, TimeUnit.SECONDS, v1BlockingQueue);

            v2BlockingQueue = new ArrayBlockingQueue<>(osuSB.getQueueCapacity());
            v2ExecutorService = new ThreadPoolExecutor(osuSB.getThreadCount(), osuSB.getMaxThreadCount(), 100L, TimeUnit.SECONDS, v2BlockingQueue);
        } else {
            useSeparateQueues = false;
            v1BlockingQueue = new ArrayBlockingQueue<>(osuSB.getQueueCapacity());
            v1ExecutorService = new ThreadPoolExecutor(osuSB.getThreadCount(), osuSB.getMaxThreadCount(), 100L, TimeUnit.SECONDS, v1BlockingQueue);
        }
    }

    public String getKeyV1() {
        return keyV1;
    }

    public String getKeyV2() {
        return keyV2;
    }

    public boolean isUsingV1() {
        return keyV1 != null;
    }

    public boolean isUsingV2() {
        return keyV2 != null;
    }

    public ArrayBlockingQueue<Runnable> getBlockingQueue() {
        if (useSeparateQueues) {
            throw new IllegalStateException("Use getV1Queue or getV2Queue if you want separate queues!");
        } else {
            return v1BlockingQueue;
        }
    }

    public ArrayBlockingQueue<Runnable> getV1Queue() {
        return v1BlockingQueue;
    }

    public ArrayBlockingQueue<Runnable> getV2Queue() {
        return v2BlockingQueue;
    }

    public ExecutorService getExecutorService() {
        if (useSeparateQueues) {
            throw new IllegalStateException("Use get V1Executor or get V2Executor if you want separate queues!");
        } else {
            return v1ExecutorService;
        }
    }

    public ExecutorService getV1Executor() {
        return v1ExecutorService;
    }

    public ExecutorService getV2Executor() {
        return v2ExecutorService;
    }

    public boolean useSeparateQueues() {
        return useSeparateQueues;
    }
}
