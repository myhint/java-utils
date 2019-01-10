package com.example.utils.datetime;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getUnixTimestampStringFromDate(Date date) {
        return new Long(date.getTime() / 1000).toString();
    }

    public static Date addDay(int day) {
        return addDate(Calendar.DAY_OF_MONTH, day);
    }

    public static String dateFormatWithyyyyMMddHHmmss(Date date) {
        return dateStringWithFormat(date, "yyyyMMddHHmmss");
    }

    public static String dateStringWithFormat(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            return formatter.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 字符串转换时间
     *
     * @param dateTimeStr
     * @param formatStr
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String dateTimeStr, String formatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr == null ? STANDARD_FORMAT : formatStr);
        Date date = null;
        try {
            date = formatter.parse(String.valueOf(dateTimeStr));
        } catch (Exception e) {
        }
        return date;
    }

    private static Date addDate(int field, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(field, value);
        return calendar.getTime();
    }
}
