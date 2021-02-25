package v2;

import osu.OsuSettings;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;

public class ApiV2Handler {
    private final String key;
    public final static String startUrl = "https://osu.ppy.sh/api/";
    private final ArrayBlockingQueue<Runnable> blockingQueue;
    private final ExecutorService executorService;

    public ApiV2Handler(OsuSettings osuS) {
        this.key = osuS.getKeyV2();

        if (osuS.useSeparateQueues()) {
            this.blockingQueue = osuS.getV2Queue();
            this.executorService = osuS.getV2Executor();
        } else {
            this.blockingQueue = osuS.getBlockingQueue();
            this.executorService = osuS.getExecutorService();
        }
    }
}
