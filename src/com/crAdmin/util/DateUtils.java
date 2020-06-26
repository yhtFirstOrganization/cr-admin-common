/*
 * 日期工具类，快速获得日期，对日期格式进行转换
 */

package com.crAdmin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class DateUtils {

    public final static String YYYY = "yyyy";
    public final static String MM = "MM";
    public final static String DD = "dd";
    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YYYY_MM = "yyyy-MM";
    public final static String YYYYMM = "yyyyMM";
    public final static String HH_MM_SS = "HH:mm:ss";
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String formatStr_yyyyMMddHHmmssS = "yyyy-MM-dd HH:mm:ss.S";
    public static String formatStr_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static String formatStr_yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static String formatStr_yyyyMMddHH = "yyyy-MM-dd HH";
    public static String formatStr_yyyyMMdd = "yyyy-MM-dd";
    public static String[] formatStr = { formatStr_yyyyMMddHHmmss,
            formatStr_yyyyMMddHHmm, formatStr_yyyyMMddHH, formatStr_yyyyMMdd };

    /**
     * 构造函数
     */
    public DateUtils() {
    }

    /**
     * 日期格式化－将<code>Date</code>类型的日期格式化为<code>String</code>型
     *
     * @param date
     *            待格式化的日期
     * @param pattern
     *            时间样式
     * @return 一个被格式化了的<code>String</code>日期
     */
    public static String format(Date date, String pattern) {
        if (date == null)
            return "";
        else
            return getFormatter(pattern).format(date);
    }

    /**
     * 默认把日期格式化成yyyy-mm-dd格式
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        if (date == null)
            return "";
        else
            return getFormatter(YYYY_MM_DD).format(date);
    }

    /**
     * 把字符串日期默认转换为yyyy-mm-dd格式的Data对象
     *
     * @param strDate
     * @return
     */
    public static Date format(String strDate) {
        Date d = null;
        if (StringUtils.isBlank(strDate))
            return null;
        else
            try {
                d = getFormatter(formatStr_yyyyMMddHHmmss).parse(strDate);
            } catch (ParseException pex) {
                return null;
            }
        return d;
    }
    /**
     * 把字符串日期默认转换为yyyy-mm-dd hh:mm:ss格式的Data对象
     *
     * @param strDate
     * @return
     */
    public static Date formatYs(String strDate) {
        Date d = null;
        if (strDate == "")
            return null;
        else
            try {
                d = getFormatter(YYYY_MM_DD_HH_MM_SS).parse(strDate);
            } catch (ParseException pex) {
                return null;
            }
        return d;
    }

    /**
     * 把字符串日期转换为f指定格式的Data对象
     *
     * @param strDate
     *            ,f
     * @return
     */
    public static Date format(String strDate, String f) {
        Date d = null;
        if (strDate == "")
            return null;
        else
            try {
                d = getFormatter(f).parse(strDate);
            } catch (ParseException pex) {
                return null;
            }
        return d;
    }

    /**
     * 日期解析－将<code>String</code>类型的日期解析为<code>Date</code>型
     *
     * @param date
     *            待格式化的日期
     * @param pattern
     *            日期样式
     * @exception ParseException
     *                如果所给的字符串不能被解析成一个日期
     * @return 一个被格式化了的<code>Date</code>日期
     */
    public static Date parse(String strDate, String pattern)
            throws ParseException {
        try {
            return getFormatter(pattern).parse(strDate);
        } catch (ParseException pe) {
            throw new ParseException(
                    "Method parse in Class DateUtils  err: parse strDate fail.",
                    pe.getErrorOffset());
        }
    }

    /**
     * 获取当前日期
     *
     * @return 一个包含年月日的<code>Date</code>型日期
     */
    public static synchronized Date getCurrDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 获取当前日期后month个月的日期
     *
     * @return 一个包含年月日的<code>Date</code>型日期
     */
    public static synchronized Date getCurrDate(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获取当前日期
     *
     * @return 一个包含年月日的<code>String</code>型日期，但不包含时分秒。yyyy-mm-dd
     */
    public static String getCurrDateStr() {
        return format(getCurrDate(), YYYY_MM_DD);
    }

    /**
     * 获得当前日期
     *
     * @return 一个包含年月日时分秒的<code>String</code>型日期。yyyymmddhhmmss
     */
    public static String getCurrDateStr_() {
        StringBuffer now_ = new StringBuffer();
        now_.append(getYear());
        now_.append(getMonth());
        now_.append(getDay());
        now_.append(format(getCurrDate(), "HH"));
        now_.append(format(getCurrDate(), "MM"));
        now_.append(format(getCurrDate(), "SS"));
        return now_.toString();
    }

    /**
     * 获取当前时间
     *
     * @return 一个包含年月日时分秒的<code>String</code>型日期。hh:mm:ss
     */
    public static String getCurrTimeStr() {
        return format(getCurrDate(), HH_MM_SS);
    }

    /**
     * 获取当前完整时间,样式: yyyy-MM-dd hh:mm:ss
     *
     * @return 一个包含年月日时分秒的<code>String</code>型日期。yyyy-MM-dd hh:mm:ss
     */
    public static String getCurrDateTimeStr() {
        return format(getCurrDate(), YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前年分 样式：yyyy
     *
     * @return 当前年分
     */
    public static String getYear() {
        return format(getCurrDate(), YYYY);
    }

    /**
     * 获取当前月分 样式：MM
     *
     * @return 当前月分
     */
    public static String getMonth() {
        String month = format(getCurrDate(), MM);
        if (month.length() == 1)
            return "0" + month;
        else
            return month;
    }

    /**
     * 获取当前月分 样式：MM
     *
     * @return 当前月分
     */
    public static String getMonth(int months) {
        String month = format(getCurrDate(months), MM);
        if (month.length() == 1)
            return "0" + month;
        else
            return month;
    }

    /**
     * 获取当前日期号 样式：dd
     *
     * @return 当前日期号
     */
    public static String getDay() {
        return format(getCurrDate(), DD);
    }

    /**
     * 按给定日期样式判断给定字符串是否为合法日期数据
     *
     * @param strDate
     *            要判断的日期
     * @param pattern
     *            日期样式
     * @return true 如果是，否则返回false
     */
    public static boolean isDate(String strDate, String pattern) {
        try {
            parse(strDate, pattern);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    /**
     * 判断给定字符串是否为特定格式年份（格式：yyyy）数据
     *
     * @param strDate
     *            要判断的日期
     * @return true 如果是，否则返回false
     */
    public static boolean isYYYY(String strDate) {
        try {
            parse(strDate, YYYY);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    public static boolean isYYYY_MM(String strDate) {
        try {
            parse(strDate, YYYY_MM);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    /**
     * 判断给定字符串是否为特定格式的年月日（格式：yyyy-MM-dd）数据
     *
     * @param strDate
     *            要判断的日期
     * @return true 如果是，否则返回false
     */
    public static boolean isYYYY_MM_DD(String strDate) {
        try {
            parse(strDate, YYYY_MM_DD);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    /**
     * 判断给定字符串是否为特定格式年月日时分秒（格式：yyyy-MM-dd HH:mm:ss）数据
     *
     * @param strDate
     *            要判断的日期
     * @return true 如果是，否则返回false
     */
    public static boolean isYYYY_MM_DD_HH_MM_SS(String strDate) {
        try {
            parse(strDate, YYYY_MM_DD_HH_MM_SS);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    /**
     * 判断给定字符串是否为特定格式时分秒（格式：HH:mm:ss）数据
     *
     * @param strDate
     *            要判断的日期
     * @return true 如果是，否则返回false
     */
    public static boolean isHH_MM_SS(String strDate) {
        try {
            parse(strDate, HH_MM_SS);
            return true;
        } catch (ParseException pe) {
            return false;
        }
    }

    /**
     * 获取一个简单的日期格式化对象
     *
     * @return 一个简单的日期格式化对象
     */
    private static SimpleDateFormat getFormatter(String parttern) {
        return new SimpleDateFormat(parttern);
    }

    /**
     * 获取给定日期的后intevalDay天的日期
     *
     * @param refenceDate
     *            给定日期（格式为：yyyy-MM-dd）
     * @param intevalDays
     *            间隔天数
     * @return 计算后的日期
     */
    public static String getNextDate(String refenceDate, int intevalDays) {
        try {
            return getNextDate(parse(refenceDate, YYYY_MM_DD), intevalDays);
        } catch (Exception ee) {
            return "";
        }
    }

    /**
     * 获取给定日期的后intevalDay天的日期
     *
     * @param refenceDate
     *            Date 给定日期
     * @param intevalDays
     *            int 间隔天数
     * @return String 计算后的日期
     */
    public static String getNextDate(Date refenceDate, int intevalDays) {
        try {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)
                    + intevalDays);
            return format(calendar.getTime(), YYYY_MM_DD);
        } catch (Exception ee) {
            return "";
        }
    }

    /**
     * 获取给定日期的后intevalMonths月的日期
     *
     * @param refenceDate
     *            Date 给定日期
     * @param intevalDays
     *            int 间隔月数
     * @return String 计算后的日期
     */
    public static String getNextMonth(Date refenceDate, int intevalMonths) {
        try {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(refenceDate);
            calendar.add(Calendar.MONTH, intevalMonths);
            return format(calendar.getTime(), YYYY_MM_DD);
        } catch (Exception ee) {
            return "";
        }
    }

    /**
     * 求当前日期和指定字符串日期的相差天数
     *
     * @param startDate
     * @return
     */
    public static long getTodayIntevalDays(String startDate) {
        try {
            // 当前时间
            Date currentDate = new Date();

            // 指定日期
            SimpleDateFormat myFormatter = new SimpleDateFormat(
                    formatStr_yyyyMMddHHmmss);
            java.util.Date theDate = myFormatter.parse(startDate);

            // 两个时间之间的天数
            long days = (currentDate.getTime() - theDate.getTime())
                    / (24 * 60 * 60 * 1000);

            return days;
        } catch (Exception ee) {
            return 0l;
        }
    }

    /**
     * 获取两个date间的天数
     *
     * @Title: getDaysBetweenTwoDate
     * @Description:
     * @author 桑越
     * @date 2015-10-22 上午9:50:01
     * @param startDate
     * @param endDate
     * @return long 返回类型
     * @throws
     * @version V1.0
     */
    public static long getDaysBetweenTwoDate(Date startDate, Date endDate) {
        try {
            // 两个时间之间的天数
            long days = (endDate.getTime() - startDate.getTime())
                    / (24 * 60 * 60 * 1000);

            return days;
        } catch (Exception ee) {
            return 0l;
        }
    }

    /**
     * 求两日期相差小时数
     *
     * @Title: getHoursBetweenTwoDate
     * @Description:
     * @author 桑越
     * @date 2015-7-1 下午3:15:02
     * @param startDate
     * @param endDate
     * @return long 返回类型
     * @throws
     * @version V1.0
     */
    public static long getHoursBetweenTwoDate(Date startDate, Date endDate) {
        try {
            long hours = (endDate.getTime() - startDate.getTime())
                    / (60 * 60 * 1000);
            return hours;
        } catch (Exception e) {
            return 0l;
        }
    }

    /**
     * 求两日期相差分钟数
     *
     * @Title: getMinutesBetweenTwoDate
     * @Description:
     * @author 方文
     * @date 2015-11-02 下午3:15:02
     * @param startDate
     * @param endDate
     * @return long 返回类型
     * @throws
     * @version V1.0
     */
    public static long getMinutesBetweenTwoDate(Date startDate, Date endDate) {
        try {
            long minutes = (endDate.getTime() - startDate.getTime())
                    / (60 * 1000);
            return minutes;
        } catch (Exception e) {
            return 0l;
        }
    }

    /**
     * 判断当前时间是否在时间内 getDates 方法
     * <p>
     * 方法说明:
     * </p>
     *
     * @param startStr
     * @param endStr
     * @return
     * @return boolean
     */
    public static boolean getDates(String startStr, String endStr, String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 获取
            Date start = sdf.parse(startStr);
            Date end = sdf.parse(endStr);
            // 获取当前时间
            Date cur = sdf.parse(time);
            if ((start.getTime() - cur.getTime() < 0)
                    && (end.getTime() - cur.getTime()) > 0)
                return true;
        } catch (Exception e) {
        }
        return false;

    }

    /**
     * 求指定日期加上指定小时数后的日期
     *
     * @param startDate
     * @param minutes
     * @return
     * @exception
     */
    public static String getDateAfterHours(String startDate, int minutes) {

        String strEndDate = "";
        try {
            // 字符串类型转换为日期类型
            SimpleDateFormat format = new SimpleDateFormat(
                    formatStr_yyyyMMddHHmmss);
            Date date = format.parse(startDate);

            // 减去制定的小时数
            Calendar cld = Calendar.getInstance();
            cld.setTime(date);
            cld.add(Calendar.MINUTE, minutes);
            Date dtEndDate = cld.getTime();
            strEndDate = format.format(dtEndDate);

        } catch (ParseException e) {

        }
        return strEndDate;
    }

    /**
     * 求指定月份的天数
     *
     * @param sDate
     *            (yyyy-MM-dd)
     * @return String
     * @exception
     */
    public static String getDayCount(String sDate) {

        // 默认设置为30天
        String dayCount = "30";
        try {
            // 字符串类型转换为日期类型
            SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD);
            Date dDate = format.parse(sDate);

            Calendar cld = Calendar.getInstance();
            cld.setTime(dDate);
            // 把日期设置为当月第一天
            cld.set(Calendar.DATE, 1);
            // 日期回滚一天，也就是最后一天
            cld.roll(Calendar.DATE, -1);

            dayCount = String.valueOf(cld.get(Calendar.DATE));

        } catch (ParseException e) {

        }
        return dayCount;
    }

}
