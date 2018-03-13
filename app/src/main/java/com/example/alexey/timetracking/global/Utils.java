package com.example.alexey.timetracking.global;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {
    private static SimpleDateFormat TIMER_FORMAT;

    static {
        TIMER_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        TIMER_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    public static String convertMillisecondsToTime(long milliseconds) {
        return TIMER_FORMAT.format(milliseconds);
    }
}
