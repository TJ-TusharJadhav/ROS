package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	

    // Utility method to return today's date in DD/MM/yyyy format
    public static String getTodayDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return today.format(formatter);
    }

    // Example usage
    public static void main(String[] args) {
        System.out.println("Today's Date: " + getTodayDate());
    }
}
