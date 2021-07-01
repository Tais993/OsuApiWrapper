package v1.entities.global;

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

    @Override
    public String toString() {
        return "Length{" +
                "timeInSeconds=" + timeInSeconds +
                '}';
    }
}