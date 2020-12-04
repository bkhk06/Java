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

//    //add by lihongwei for ��ȡtimePara[] ʱ��ڵ㵽�����ļ� 20190218 start
//    private static Properties properties = PropertyUtil.loadProps("flight.properties");
//    //add by lihongwei for ��ȡtimePara[] ʱ��ڵ㵽�����ļ� 20190218 end

    /**
     * ����ָ��ʱ����ĳ��ʱ��
     *
     * @param time       ָ��ʱ��
     * @param dateFormat ʱ���ʽ
     * @param timeSpan   ʱ��εĳ���
     * @return ���������ʱ�� ��ʽΪ yyyyMMddHHmm
     */
    public static String getTimeAddMin(String time, SimpleDateFormat dateFormat, int timeSpan) {


        GregorianCalendar now = new GregorianCalendar();// ����ʱ��

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
     * ���ط�Null �ַ���
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
     * ����ָ��ʱ����ĳ��ʱ��
     *
     * @param yyyyMMddHHmm ָ��ʱ��
     * @param timeSpan     ʱ��εĳ���
     * @return ���������ʱ�� ��ʽΪ yyyyMMddHHmm
     */
    public static String getTimeAddMin(String yyyyMMddHHmm, int timeSpan) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        GregorianCalendar now = new GregorianCalendar();// ����ʱ��

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
     * �����ַ���ʱ���
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
     * ת��Stringʱ����Calendar
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
//     * ��ȡԤ��/�����ʱ�䷶Χ
//     *
//     * @return <ul>
//     * <li>dateArray[0]: ��ǰʱ���ǰ1��Сʱ</li>
//     * <li>dateArray[1]: ��ǰʱ����5��Сʱ</li>
//     * <li>dateArray[2]: ��ǰʱ��</li>
//     * </ul>
//     */
//    public static String[] getPredictTimeSpan() {
//
//        //add by lihongwei for ��ȡtimePara[] ʱ��ڵ㵽�����ļ� 20190218 start
//
//        int earlistTime = PropertyUtil.getInt(properties, "flight.slotAssign.earlistTime");
//        int lastTime = PropertyUtil.getInt(properties, "flight.slotAssign.lastTime");
//        //add by lihongwei for ��ȡtimePara[] ʱ��ڵ㵽�����ļ� 20190218 end
//
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
//        String[] dateArray = new String[3];
//        // ����ʱ��
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
     * ͨ���ַ���ʽ�жϻ����Ƿ�Ϊ��½������null ���� FALSE
     *
     * @param ap ����������
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
     * ����ʱ�����ַ�����������ֵ
     *
     * @param sList �ַ�������
     * @return ���ֵ
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
     * ����ʱ�����ַ����������Сֵ������Null Null ������
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
     * �ڸ���ʱ�䷶Χ�ڣ�����ָ�����ӵ�ʱ϶�б�
     *
     * @param startTime  ��ʼʱ�� 12λyyyyMMddHHmm
     * @param endTime    ����ʱ�� 12λyyyyMMddHHmm
     * @param assignMins ָ���ķ��ӣ���ʽ�磺"00,05,10"
     * @param maxMinute  ���޽���ʱ��ʱ��������ķ�����
     * @return
     */
    public static List<String> getAssignSlots(String startTime, String endTime,
                                              String assignMins, int maxMinute) {
        // ʱ���ʽ
        SimpleDateFormat format12 = TimeUtil.getSimpleDateFormat12();
        // �������ؽ��
        List<String> assignSlots = new ArrayList<String>();
        if (SlotUtil.isEmptyStr(startTime)) {
            return assignSlots;
        }
        if (SlotUtil.isEmptyStr(endTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");

            Calendar now = Calendar.getInstance();// ����ʱ��

            String nowTime = sdf.format(now.getTime());
            endTime = TimeUtil.addStringTime(nowTime, Calendar.MINUTE, maxMinute, format12);
        }
        // ������������
        String[] assignMinsArr = assignMins.split(",");
        // ��ʱ����
        String tempTime = startTime;
        while (tempTime.substring(0, 10).compareTo(endTime.substring(0, 10)) <= 0) {
            // �����������ӣ�����ʱ����ʱ���yyyyMMddHHʱ��ƴ��
            // ������ֹʱ��ȶԣ�������Чʱ��
            for (String assignMin : assignMinsArr) {
                String tempSlot = tempTime.substring(0, 10) + assignMin;
                if (TimeUtil.isInRange(tempSlot, startTime, endTime, true, false, format12)) {
                    assignSlots.add(tempSlot);
                }
            }
            // ����1Сʱ
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
