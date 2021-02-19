package v1.entities.global;

import java.util.concurrent.TimeUnit;

public class Length {
    private final long timeInSeconds;

    public Length(long timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public long getTimeInSeconds() {
        return timeInSeconds;
    }

    public long getTimeInMinutes() {
        return timeInSeconds/60;
    }

    public String getTimeAsMinuteSeconds() {
        String minute = TimeUnit.SECONDS.toMinutes(timeInSeconds) - (TimeUnit.SECONDS.toHours(timeInSeconds)* 60) + "";
        String second = TimeUnit.SECONDS.toSeconds(timeInSeconds) - (TimeUnit.SECONDS.toMinutes(timeInSeconds) *60) + "";

        if (minute.length() == 1) {
            minute = "0" + minute;
        }

        if (second.length() == 1) {
            second = "0" + second;
        }

        return minute + ":" + second;
    }

    @Override
    public String toString() {
        return "Length{" +
                "timeInSeconds=" + timeInSeconds +
                '}';
    }
}