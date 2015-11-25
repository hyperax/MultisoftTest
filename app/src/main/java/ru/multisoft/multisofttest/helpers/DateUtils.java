package ru.multisoft.multisofttest.helpers;

public class DateUtils {

    private DateUtils() {
    }

    public static long getDateMills() {
        return System.currentTimeMillis();
    }

    public static long getDateSeconds() {
        return System.currentTimeMillis() / 1000;
    }
}
