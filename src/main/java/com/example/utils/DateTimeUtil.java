package com.example.utils;

import java.util.Calendar;
import java.util.TimeZone;

public class DateTimeUtil {
    public static String getCurrentTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static Long getFirstDayOfPreviousMonthTimestamp() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+5"));
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static Long getLastDayOfPreviousMonthTimestamp() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+5"));
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
}
