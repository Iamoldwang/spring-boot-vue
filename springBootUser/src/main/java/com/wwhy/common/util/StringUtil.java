package com.wwhy.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;


public class StringUtil {

    /**
     * 隐藏手机号码中间4位
     *
     * @param phone
     * @return
     * @author wangpan
     * create date:2018年1月18日
     */
    public static String hidePhone(String phone) {
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 隐藏身份证号码中间8位
     *
     * @param idNumber
     * @return
     * @author wangpan
     * create date:2018年1月19日
     */
    public static String hideIdNumber(String idNumber) {
        return idNumber.replaceAll("(\\d{3})\\d{11}(\\d{4}||\\d{3}X)", "$1********$2");
    }

    /**
     * 隐藏APPID中间8位
     *
     * @param idNumber
     * @return
     * @author wangpan
     * create date:2018年1月19日
     */
    public static String hideAppId(String appId) {
        return appId.replaceAll("(\\d{4})\\d{8}(\\d{4})", "$1********$2");
    }

    /**
     * 隐藏秘钥(前后留10位)
     *
     * @param idNumber
     * @return
     * @author wangpan
     * create date:2018年1月19日
     */
    public static String hideKey(String key) {
        String str = null;
        if (key.length() > 30) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if (i >= 10 && i < (key.length() - 10)) {
                    sb.append("*");
                } else {
                    sb.append(c);
                }
            }
            str = sb.toString();
        }
        return str;
    }

    public static void main(String[] args) {
        String a = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAud+AKjWkj5I8zYYjmvTOtPtWEIB1X7VlJX9KKJGvHlvjgU+5CX9M2v26l1acuMdXiSFi0ZS0JMXdib/Uo9ML9B7Te3NZ0tX5NEVnISPIu1pEKNJTQpvOExX1Mkg5qHCUe3dyuNJZUuyTUNQCFkigBzxH/ievCDHjkPN9kogqFjFG8pBV4sbAxU10lfCcZA701wxboi0KG2fbJVYgaga4SAu9qZyWk1Q5GSQEZhYAx1aqB4RRx3JPMMDhZmQ9oM9YJtfZTenxqTXF0H7Gy2mWQRwW1FE177iCymUDdav5SklR8cInSjxddrpwC4/ViFJ4sLzvODD29zdGa5Oom9LE7wIDAQAB";
        String b = autoSupplementCode(13, 4);
        System.out.println(b);
    }


    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(1 - 男 ， 2 - 女)
     */
    public static Byte getGenderByIdCard(String idCard) {
        Byte sGender = null;

        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sGender = 1;//男
        } else {
            sGender = 2;//女
        }
        return sGender;
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     * @author ：shijing
     * 2016年12月5日下午4:34:46
     */
    public static boolean isMobile(final String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        Pattern pattern = null;
        Matcher matcher = null;
        boolean flag = false;
        pattern = Pattern.compile("^[1][0-9]{10}$"); // 验证手机号  
        matcher = pattern.matcher(str);
        flag = matcher.matches();
        return flag;
    }

    /**
     * @param code
     * @param num
     * @param obj
     * @return
     * @description:自动补0
     * @time:2018年3月12日 下午3:18:48
     * @user:WangPan
     */
    public static String autoSupplementCode(int code, int num) {
        String result = "";
        result = String.format("%0" + num + "d", code);
        return result;
    }
}
