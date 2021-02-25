package osu;

public class OsuSettingsBuilder {
    private String keyV1 = "";
    private String keyV2 = "";

    private int threadCount = 1;
    private int maxThreadCount = 10;
    private int queueCapacity = 100;

    private boolean separateQueues = false;

    public OsuSettingsBuilder setKeyV1(String keyV1) {
        this.keyV1 = keyV1;
        return this;
    }

    protected String getKeyV1() {
        return keyV1;
    }

    public OsuSettingsBuilder setKeyV2(String keyV2) {
        this.keyV2 = keyV2;
        return this;
    }

    protected String getKeyV2() {
        return keyV2;
    }

    public OsuSettingsBuilder setThreadCount(int threadCount) {
        this.threadCount = threadCount;
        return this;
    }

    protected int getThreadCount() {
        return threadCount;
    }

    public OsuSettingsBuilder setMaxQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
        return this;
    }

    protected int getQueueCapacity() {
        return queueCapacity;
    }

    public OsuSettingsBuilder setMaxThreadCount(int maxThreadCount) {
        this.maxThreadCount = maxThreadCount;
        return this;
    }

    protected int getMaxThreadCount() {
        return maxThreadCount;
    }

    public OsuSettingsBuilder isSeparateQueues(boolean separateQueues) {
        this.separateQueues = separateQueues;
        return this;
    }

    protected boolean isSeparateQueues() {
        return separateQueues;
    }

    public OsuSettings build() {
        return new OsuSettings(this);
    }
}
