package dev.restfil.api.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private DateUtils(){};
    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date getCurrentDateTime() {
        return new Date();
    }

    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public static Date parseDate(String dateStr) throws ParseException {
        return dateFormat.parse(dateStr);
    }

    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static long getTimeStamp() {
        return  System.currentTimeMillis();
    }

    public static int daysBetweenDates(Date date1, Date date2) {
        long diff = date2.getTime() - date1.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }
}
