package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
    public static Date addDays(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d yyyy");
        return dateFormat.format(date);
    }

    public static String formatDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d yyyy HH:mm");
        return dateFormat.format(date);
    }

    public static Date parseStringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dateFormat.parse(dateString);
    }
}
