package com.rayhahah.libbase.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期类的时间操作
 *
 * @author Administrator
 */
public class DateTimeUitl {
    public final static String SYS_DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";

    public final static String TIME_WITH_SECOND_FORMATE = "yyyy-MM-dd HH:mm";

    public final static long INTERVAL_FIFTEEN_MIN = 15;
    public final static long INTERVAL_HALF_HOUR = 30;
    public final static long INTERVAL_ONE_HOUR = 60;
    public final static long INTERVAL_THREE_HOUR = 180;
    public final static long INTERVAL_EIGHT_HOUR = 480;
    public final static long INTERVAL_TWELVE_HOUR = 720;
    public final static long INTERVAL_DAY = 1440;


    public final static long INTERVAL_MILLS_FIVE_MIN = 5 * 60 * 1000;
    public final static long INTERVAL_MILLS_FIFTEEN_MIN = 15 * 60 * 1000;
    public final static long INTERVAL_MILLS_HALF_HOUR = 30 * 60 * 1000;
    public final static long INTERVAL_MILLS_ONE_HOUR = 1 * 60 * 60 * 1000;
    public final static long INTERVAL_MILLS_THREE_HOUR = 3 * 60 * 60 * 1000;
    public final static long INTERVAL_MILLS_EIGHT_HOUR = 8 * 60 * 60 * 1000;
    public final static long INTERVAL_MILLS_TWELVE_HOUR = 12 * 60 * 60 * 1000;
    public final static long INTERVAL_MILLS_DAY = 24 * 60 * 60 * 1000;

    /**
     * 返回当前时间序列
     *
     * @return
     */
    public static String getTimeSeq() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String timeSeqFormat(long timeseq) {
        return timeSeqFormat(timeseq, SYS_DATE_FORMATE);
    }

    public static String timeSeqFormat(String timeseq) {
        return timeSeqFormat(Long.parseLong(timeseq), SYS_DATE_FORMATE);
    }

    public static String timeSeqFormat(long timeseq, String format) {
        Date date = new Date(timeseq);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String time = formatter.format(date);
        return time;
    }


    /**
     * 获得当前指定格式的时间字符串
     *
     * @param formate 如yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentWithFormate(String formate) {
        String time = "";
        Date dNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(formate);
        time = formatter.format(dNow);
        return time;
    }


    /**
     * 得到程序中标准时间
     *
     * @return yyyy-MM-dd 格式的时间
     */
    public static String getCurrentTimeToDay() {
        String time = "";
        Date dNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        time = formatter.format(dNow);
        return time;
    }

    /**
     * 得到程序中标准时间到小时
     *
     * @return yyyy-MM-dd hh格式的时间
     */
    public static String getCurrentTimesToHour() {
        String time = "";
        Date dNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh");
        time = formatter.format(dNow);
        return time;
    }

    /**
     * 得到程序中标准时间到分
     *
     * @return yyyy-MM-dd hh:mm 格式的时间
     */
    public static String getCurrentTimeToMinute() {
        String time = null;
        Date dNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        time = formatter.format(dNow);
        return time;
    }

    /**
     * 得到程序中标准时间到秒
     *
     * @return yyyy-MM-dd HH:mm:ss 格式的时间
     */
    public static String getCurrentTimeToSecond() {
        String time = "";
        Date dNow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        time = formatter.format(dNow);
        return time;
    }

    /**
     * 得到程序中标准时间到分
     *
     * @return yyyy年MM月dd日hh时mm分 格式的时间
     */
    public static String getTimeToDay() {
        String time = "";
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);  // 4 表示 2004 年
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        time = year + "年" + month + "月" + day + "日";
        return time;
    }

    /**
     * 得到程序中标准时间到分
     *
     * @return yyyy年MM月dd日hh时mm分 格式的时间
     */
    public static String getTimeToMinute() {
        String time = "";
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);  // 4 表示 2004 年
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        time = year + "年" + month + "月" + day + "日" + hour + "时" + minute + "分";
        return time;
    }

    /**
     * 得到程序中标准时间到分
     *
     * @return yyyy年MM月dd日hh时mm分 格式的时间
     */
    public static String getTimeToSecond() {
        String time = "";
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);  // 4 表示 2004 年
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        time = year + "年" + month + "月" + day + "日" + hour + "时" + minute + "分" + second + "秒";
        return time;
    }


    /**
     * 获取当前时间的n天前的日期
     *
     * @param day
     * @return
     */
    public static String getBeforeCurentTimes(int day) {
        String time = null;
        Calendar calendar = calGetCalendarTime();
        calendar.add(Calendar.DATE, -day);
        time = getSysDateTime(calendar.getTime());
        return time;
    }

    /**
     * 是否是系统时间
     *
     * @param time
     * @return
     */
    public static boolean isSysDateTime(String time) {
        boolean flag = false;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(TIME_WITH_SECOND_FORMATE);
            formatter.parse(time);
            flag = true;
        } catch (Exception ex) {
            flag = false;
        }
        return flag;
    }

    /**
     * 将int数组的  年份、月份、日，转换为  2017-06-05 的格式
     *
     * @return
     */
    public static String formatDateFromInt(int year, int month, int day) {
        String m = "";
        if (month >= 10) {
            m = "" + month;
        } else {
            m = "0" + month;
        }

        String d = "";
        if (day >= 10) {
            d = "" + day;
        } else {
            d = "0" + day;
        }
        LogUtils.e("3!! y=" + year + ",m=" + m + ",d=" + d);
        String result = year + "-" + m + "-" + d;
        return result;
    }


    /**
     * 得到当前时间的Calendar形式
     *
     * @return Calendar
     */
    public static Calendar calGetCalendarTime() {
        Calendar caltime = Calendar.getInstance();
        return caltime;
    }

    /**
     * 获得系统默认格式的日期字符串
     *
     * @param date
     * @return
     */
    public static String getSysDateTime(Date date) {
        String time = null;
        SimpleDateFormat formatter = new SimpleDateFormat(SYS_DATE_FORMATE);
        time = formatter.format(date);
        return time;
    }

    /**
     * 将时间字符串转化为毫秒值（相对 1970年１月１日）
     *
     * @param time 时间字符串
     * @return　　　失败返回　０
     */
    public static long toSystemTimeLongMi(String time) {
        long nRet = 0;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(SYS_DATE_FORMATE);
            Date de = dateFormat.parse(time);
            nRet = de.getTime();
        } catch (Exception e) {/*DISCARD EXCEPTION*/
        }
        return nRet;
    }

    /**
     * 将时间字符串转化为秒值（相对 1970年１月１日）
     *
     * @param time 时间字符串
     * @return　　　失败返回　０
     */
    public static long toSystemTimeLong(String time) {
        long nRet = 0;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(SYS_DATE_FORMATE);
            Date de = dateFormat.parse(time);
            nRet = de.getTime() / 1000;
        } catch (Exception e) {/*DISCARD EXCEPTION*/
        }
        return nRet;
    }

    /**
     * 时间格式转换
     *
     * @param ltime - yyyy-MM-dd HH:mm:ss
     * @return String - yyyy年MM月dd日 HH时:mm分
     */
    public static String getTimeWithFormat(String ltime) {
        String ftime = "";
        if (ltime.length() >= 18) {
            ftime = ltime.substring(0, 4) + "年" + ltime.substring(5, 7) + "月"
                    + ltime.substring(8, 10) + "日" + ltime.substring(11, 13) + "时"
                    + ltime.substring(14, 16) + "分";
        }
        return ftime;

    }

    /**
     * 得出2个时间相差多少毫秒数
     */
    public static Long getDistanceMills(long now, long startTime) {
        return now - startTime;
    }

    /**
     * 得出2个时间相差多少分钟
     *
     * @param ltime -
     * @param str1  时间参数 1 格式：2011-11-11 11:11:11
     * @param str2  时间参数 2 格式：2011-01-01 12:00:00
     * @return Long
     */
    public static Long getDistanceMin(String now, String callTime) {
        long min = -1;
        long time1 = toSystemTimeLong(now);
        long time2 = toSystemTimeLong(callTime);
        min = ((time1 - time2) / (60));
        return min;
    }

    /**
     * 得出2个时间相差多少秒
     *
     * @return boolean
     */
    public static Long getDistanceSec(String now, String callTime) {
        long second = -1;
        long time1 = toSystemTimeLong(now);
        long time2 = toSystemTimeLong(callTime);
        second = (time1 - time2);
        return second;
    }


    /**
     * 获取某个时间后的n小时
     *
     * @return
     */
    public static String getTimeLaterH(String time, int n) {

        long currentTime = toSystemTimeLongMi(time);
        currentTime += n * 60 * 60 * 1000;
        Date date = new Date(currentTime);
        return getSysDateTime(date);
    }


    public static String secondToMinuteOrHour(long seconds) {
        //60S之内
        if (seconds < 60) {
            return String.format("00:%s", seconds < 10 ? "0" + seconds : seconds);
        } else {
            //60分钟以内
            if (seconds / 60 < 60) {
                return String.format("%s:%s", (seconds / 60) < 10 ? "0" + seconds / 60 : seconds / 60, seconds % 60 < 10 ? "0" + seconds % 60 : seconds % 60);
            } else {
                //60分钟之后
                return String.format("%s:%s:%s", seconds / 360,
                        (seconds - (seconds / 360) * 360) / 60 < 10 ? "0" + (seconds - (seconds / 360) * 360) / 60 : (seconds - (seconds / 360) * 360) / 60,
                        seconds % 60 < 10 ? "0" + seconds % 60 : seconds % 60);
            }
        }
    }

    /**
     * 验证日期是否合法
     *
     * @param timeStr
     * @return
     */
    public static boolean isLeaglDate(String timeStr) {
        boolean flag = true;
        if (timeStr != null) {
            String check = "\\d{4}-\\d{2}-\\d{2}";
            Pattern timePar = Pattern.compile(check);
            Matcher matcher = timePar.matcher(timeStr);
            flag = matcher.matches();
        }
        return flag;
    }

    /**
     * 验证日期时间是否合法
     *
     * @param timeStr
     * @return
     */
    public static boolean isLeaglDateTime(String timeStr) {
        boolean flag = true;
        String check = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}((:| )\\d{3}+)?";
        Pattern timePar = Pattern.compile(check);
        Matcher matcher = timePar.matcher(timeStr);
        flag = matcher.matches() || isLeaglDate(timeStr);
        return flag;
    }

    public static void main(String[] args) {
        String oldTime = "1530587098705";
        timeSeqFormat(oldTime);
        System.out.println(getDistanceMin(getCurrentTimeToSecond(), "2018-07-03 09:04:00"));
    }
}
