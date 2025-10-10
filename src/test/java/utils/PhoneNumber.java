package utils;

import java.util.concurrent.atomic.AtomicLong;

public class PhoneNumber {
    private static final AtomicLong counter = new AtomicLong(0);
    private static final int[] prefixes = {6, 7, 8, 9};

    public static String generateUniquePhoneNumber() {
        int prefix = prefixes[(int)(System.nanoTime() % prefixes.length)];
        long uniquePart = (System.currentTimeMillis() % 1000000000L) + counter.getAndIncrement();
        String number = prefix + String.format("%09d", uniquePart % 1000000000L); // ensure 9 digits
        return number.substring(0, 10); // safety crop to 10 digits
    }
}

