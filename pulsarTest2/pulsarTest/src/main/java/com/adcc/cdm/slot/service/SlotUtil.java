package com.adcc.cdm.slot.service;

import com.adcc.common.util.TimeUtil;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SlotUtil {
    /**
     *
     */
    private static final Logger logger = Logger.getLogger(SlotUtil.class);

//    //add by lihongwei for 提取timePara[] 时间节点到配置文件 20190218 start
//    private static Properties properties = PropertyUtil.loadProps("flight.properties");
//    //add by lihongwei for 提取timePara[] 时间节点到配置文件 20190218 end

    /**
     * 返回指定时间后的某个时间
     *
     * @param time       指定时间
     * @param dateFormat 时间格式
     * @param timeSpan   时间段的长度
     * @return 产生结果的时间 格式为 yyyyMMddHHmm
     */
    public static String getTimeAddMin(String time, SimpleDateFormat dateFormat, int timeSpan) {


        GregorianCalendar now = new GregorianCalendar();// 现在时间

        try {
            now.setTime(dateFormat.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        now.add(GregorianCalendar.MINUTE, timeSpan);
        return dateFormat.format(now.getTime());

    }

    /**
     * 返回非Null 字符串
     *
     * @param str
     * @return
     */
    public static String getNotNullString(String str) {

        if (str == null) {
            return "";
        }
        return str;

    }

    /**
     * 返回指定时间后的某个时间
     *
     * @param yyyyMMddHHmm 指定时间
     * @param timeSpan     时间段的长度
     * @return 产生结果的时间 格式为 yyyyMMddHHmm
     */
    public static String getTimeAddMin(String yyyyMMddHHmm, int timeSpan) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        GregorianCalendar now = new GregorianCalendar();// 现在时间

        try {
            now.setTime(sdf.parse(yyyyMMddHHmm));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            logger.error("", e);

        }

        now.add(GregorianCalendar.MINUTE, timeSpan);
        return sdf.format(now.getTime());

    }


    /**
     * 计算字符串时间差
     *
     * @param time1
     * @param time2
     * @param dateFormat
     * @param calendarType
     * @return
     */
    public static Long calculateStrTimeDiff(String time1, String time2, SimpleDateFormat dateFormat, int calendarType) {
        Calendar cal1 = transStrToCal(time1, dateFormat);
        Calendar cal2 = transStrToCal(time2, dateFormat);

        long diff = cal1.getTimeInMillis() - cal2.getTimeInMillis();

        if (Calendar.MILLISECOND == calendarType) {
            return diff;
        } else if (Calendar.SECOND == calendarType) {
            return diff / 1000;
        } else if (Calendar.MINUTE == calendarType) {
            return diff / 1000 / 60;
        }

        return null;
    }

    /**
     * 转换String时间至Calendar
     *
     * @param time
     * @param dateFormat
     * @return
     */
    public static Calendar transStrToCal(String time, SimpleDateFormat dateFormat) {
        dateFormat.setLenient(false);
        Date date;
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static boolean isEmptyStr(String s) {

        if (s == null) {
            return true;
        }

        if (s.trim().equals("")) {
            return true;
        }

        return false;
    }

    public static boolean isNotEmptyStr(String s) {

        if (isEmptyStr(s)) {
            return false;
        }
        return true;
    }

//    /**
//     * 获取预测/计算的时间范围
//     *
//     * @return <ul>
//     * <li>dateArray[0]: 当前时间的前1个小时</li>
//     * <li>dateArray[1]: 当前时间后的5个小时</li>
//     * <li>dateArray[2]: 当前时间</li>
//     * </ul>
//     */
//    public static String[] getPredictTimeSpan() {
//
//        //add by lihongwei for 提取timePara[] 时间节点到配置文件 20190218 start
//
//        int earlistTime = PropertyUtil.getInt(properties, "flight.slotAssign.earlistTime");
//        int lastTime = PropertyUtil.getInt(properties, "flight.slotAssign.lastTime");
//        //add by lihongwei for 提取timePara[] 时间节点到配置文件 20190218 end
//
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
//        String[] dateArray = new String[3];
//        // 现在时间
//        Calendar now = Calendar.getInstance();
//        dateArray[2] = sdf.format(now.getTime());
//        now.set(GregorianCalendar.MINUTE, 0);
//        //now.add(GregorianCalendar.HOUR_OF_DAY, -1);
//        now.add(GregorianCalendar.MINUTE, earlistTime != 0 ? earlistTime : -60);
//        dateArray[0] = sdf.format(now.getTime());
//        //now.add(GregorianCalendar.HOUR_OF_DAY, 6);
//        now.add(GregorianCalendar.MINUTE, lastTime != 0 ? lastTime + 60 : 360);
//        dateArray[1] = sdf.format(now.getTime());
//        return dateArray;
//    }

    /**
     * 通过字符方式判断机场是否为大陆机场，null 返回 FALSE
     *
     * @param ap 机场四字码
     * @return
     */
    public static boolean IsAPofChinaMainLandByCode(String ap) {

        if (ap == null) {
            return false;

        }

        return ((ap.startsWith("Z") && !ap.startsWith("ZK") && !ap
                .startsWith("ZM")));

    }


    /**
     * 返回时间子字符串数组的最大值
     *
     * @param sList 字符串数组
     * @return 最大值
     */
    public static String sortMaxDateString(List<String> sList) {
        List<String> resultList = new ArrayList<String>();
        for (String s : sList) {
            if (SlotUtil.isEmptyStr(s)) {
                //   resultList.add(SlotUtil.NULLDateString);
            } else {
                resultList.add(s);
            }
        }
        Collections.sort(resultList,
                new Comparator<String>() {
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
        if (resultList.size() > 0) {
            return resultList.get(resultList.size() - 1);
        }
        return null;
    }

    /**
     * 返回时间子字符串数组的最小值，若有Null Null 不参与
     *
     * @param sList
     * @return
     */
    public static String sortMinDateString(List<String> sList) {

        List<String> resultList = new ArrayList<String>();
        for (String s : sList) {

            if (SlotUtil.isEmptyStr(s)) {
                //  resultList.add(s);
            } else {

                resultList.add(s);
            }

        }


        Collections.sort(resultList,
                new Comparator<String>() {
                    public int compare(String o1, String o2) {

                        return o1.compareTo(o2);
                    }
                });


        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;


    }

    /**
     * 在给定时间范围内，生成指定分钟的时隙列表
     *
     * @param startTime  开始时间 12位yyyyMMddHHmm
     * @param endTime    结束时间 12位yyyyMMddHHmm
     * @param assignMins 指定的分钟，格式如："00,05,10"
     * @param maxMinute  当无结束时间时，最多分配的分钟数
     * @return
     */
    public static List<String> getAssignSlots(String startTime, String endTime,
                                              String assignMins, int maxMinute) {
        // 时间格式
        SimpleDateFormat format12 = TimeUtil.getSimpleDateFormat12();
        // 创建返回结果
        List<String> assignSlots = new ArrayList<String>();
        if (SlotUtil.isEmptyStr(startTime)) {
            return assignSlots;
        }
        if (SlotUtil.isEmptyStr(endTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

            Calendar now = Calendar.getInstance();// 现在时间

            String nowTime = sdf.format(now.getTime());
            endTime = TimeUtil.addStringTime(nowTime, Calendar.MINUTE, maxMinute, format12);
        }
        // 解析给定分钟
        String[] assignMinsArr = assignMins.split(",");
        // 临时变量
        String tempTime = startTime;
        while (tempTime.substring(0, 10).compareTo(endTime.substring(0, 10)) <= 0) {
            // 迭代给定分钟，与临时变量时间的yyyyMMddHH时间拼接
            // 并与起止时间比对，保留有效时间
            for (String assignMin : assignMinsArr) {
                String tempSlot = tempTime.substring(0, 10) + assignMin;
                if (TimeUtil.isInRange(tempSlot, startTime, endTime, true, false, format12)) {
                    assignSlots.add(tempSlot);
                }
            }
            // 步进1小时
            tempTime = TimeUtil.addStringTime(tempTime, Calendar.HOUR_OF_DAY, 1, format12);
        }
        return assignSlots;
    }

    public static final String NULLDateString = "000000000000";

//    public static void main(String[] args) {
//        List<String> l = new ArrayList<String>();
//        //	l.add("1");
//        //	l.add(null);
//        //	l.add(null);
//        //	System.out.println(l.size());
//        //	System.out.println(SlotUtil.sortMinDateString(l));
//        String n = null;
//        String m = null;
//        // System.out.println( m.compareTo(n));
//        List<String> s = SlotUtil.getAssignSlots("201506091000", "201506091200", "15,45", 180);
//
//
//        System.out.println(s);
//        //  System.out.println( SlotUtil.belongWhichAssignSlot(s, "201506091000"));
//        System.out.println(AbstractSlotAllocateServiceBean.getNextAvailabeSlot(s, "201506091000", null, null));
//    }


}
