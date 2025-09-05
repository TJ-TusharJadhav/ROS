package utils;

import java.util.Random;

public class TestUtils {
    public static String generateUniquePhoneNumber() {
        Random rand = new Random();
        long num = 6000000000L + (long) (rand.nextDouble() * 3999999999L);
        return String.valueOf(num);
    }
}
