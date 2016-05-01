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
public class DateTimeUtil {
    public static String FORMAT_DEFAULT_YMD = "yyyy-MM-dd";
    public static String FORMAT_DEFAULT_YMD_MIN = "yyyyMMdd";
    public static String FORMAT_DEFAULT_MIN = "yyyyMMddHHmmss";
    public static String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    public static Timestamp tranDate(String dateStr, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
           date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
        }
        if (date == null){
            return null;
        }else{
            return Timestamp.valueOf(new SimpleDateFormat(FORMAT_DEFAULT).format(date));
        }
    }

    public static void main(String[] args){
        System.out.println(tranDate("20130506", FORMAT_DEFAULT_YMD_MIN));
    }
}
