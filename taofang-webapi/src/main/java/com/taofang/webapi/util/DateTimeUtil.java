package com.taofang.webapi.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-05-01
 */
public class DatetimeUtil {
    public static String FORMAT_DEFAULT_YMD = "yyyy-MM-dd";
    public static String FORMAT_DEFAULT_YMD_MIN = "yyyyMMdd";
    public static String FORMAT_DEFAULT_MIN = "yyyyMMddHHmmss";
    public static String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    public static Timestamp tranDatetimeStr(String datetimeStr, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(datetimeStr);
        } catch (ParseException e) {
        }
        if (date == null){
            return new Timestamp(System.currentTimeMillis());
        }else{
            return Timestamp.valueOf(new SimpleDateFormat(FORMAT_DEFAULT).format(date));
        }
    }

    public static Timestamp tranDate(String dateStr, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
           date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
        }
        if (date == null){
            return new Timestamp(System.currentTimeMillis());
        }else{
            return Timestamp.valueOf(new SimpleDateFormat(FORMAT_DEFAULT).format(date));
        }
    }

    public static String tranTimestamp(Timestamp timestamp, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date(timestamp.getTime());
        return dateFormat.format(date);
    }

    public static String tranCurrentStrMIN(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DEFAULT_YMD_MIN);
        return dateFormat.format(date);
    }

    public static void main(String[] args){
        Timestamp timestamp = tranDate("2013-05-06 11:23:42", FORMAT_DEFAULT);
        System.out.println(tranTimestamp(timestamp, FORMAT_DEFAULT));
        System.out.println(tranCurrentStrMIN());
    }
}
