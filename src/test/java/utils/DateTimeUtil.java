package utils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateTimeUtil {

    public static Map<String, String> getFormattedDateTimePlus20Mins() {
        // Get current time
        Calendar calendar = Calendar.getInstance();

        // Add 10 minutes
        calendar.add(Calendar.MINUTE, 20);
        Date futureTime = calendar.getTime();

        // Format 1: dd/MM/yyyy hh:mm a (Enterdatetime) - leading zero for hours
        SimpleDateFormat enterFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        String Enterdatetime = enterFormat.format(futureTime);

        // Format 2: MMM d, yyyy, h:mm a (Verifydatetime) - NO leading zero for hours
        SimpleDateFormat verifyFormat = new SimpleDateFormat("MMM d, yyyy, h:mm a");
        String Verifydatetime = verifyFormat.format(futureTime);

        // Store both formats in a map
        Map<String, String> dateMap = new HashMap<>();
        dateMap.put("Enterdatetime", Enterdatetime);
        dateMap.put("Verifydatetime", Verifydatetime);

        return dateMap;
    }

    // public static void main(String[] args) {
    //     Map<String, String> dateTimeMap = getFormattedDateTimePlus20Mins();
    //     System.out.println("Enterdatetime: " + dateTimeMap.get("Enterdatetime"));
    //     System.out.println("Verifydatetime: " + dateTimeMap.get("Verifydatetime"));
    // }
}



