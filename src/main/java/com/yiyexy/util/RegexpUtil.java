package com.yiyexy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Created on 2017/5/9.</p>
 *
 * @author stormma
 *
 * @description: 正则匹配工具类
 */
public class RegexpUtil {

    /**
     * 判断是不是手机号码
     * @param mobile
     * @return
     */
    public static boolean isMobileNum(String mobile) {

        String regexp = "^1[3,4,5,7,8]\\d{9}$";

        Pattern pattern = Pattern.compile(regexp);

        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }
}
