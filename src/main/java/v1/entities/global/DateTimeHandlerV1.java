package v1.entities.global;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandlerV1 {

    public static LocalDateTime getDateTimeFromString(String timeAsString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(timeAsString, formatter);
    }
}
