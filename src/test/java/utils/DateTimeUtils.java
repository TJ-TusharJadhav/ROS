package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    // Returns today's date in yyyy-MM-dd (for date picker)
    public static String getTodayDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // Returns current time + 10 minutes in HH:mm (for input[type='time'])
    public static String getFutureTime() {
        return LocalTime.now().plusMinutes(10).format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    // Returns expected formatted datetime (for history validation)
    public static String getExpectedDateTime() {
        LocalDate today = LocalDate.now();
        LocalTime futureTime = LocalTime.now().plusMinutes(10);
        LocalDateTime dateTime = LocalDateTime.of(today, futureTime);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy, h:mm a");
        return dateTime.format(outputFormatter);
    }
}
