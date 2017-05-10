package com.yiyexy.util;

import java.util.Calendar;
import java.util.Date;
/**
 * <p>Created on 2017/5/10.</p>
 *
 * @author StormMa
 *
 * @Description: 日期相关工具类
 */
public class DateUtils {

    /**
     * 判断两个日期像个多少天
     * @param date1
     * @param date2
     * @return
     */
    public static int getDifferenceDays(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
    }

    /**
     * <p>获得指定日期之前几天的日期</p>
     * @param date
     * @param day
     * @return
     */
    public static Date getBeforeDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int days = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, days - day);
        return calendar.getTime();
    }
}