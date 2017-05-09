package com.yiyexy.util;

import java.util.Random;

/**
 * <p>Created on 2017/5/8.</p>
 *
 * @author stormma
 * @description: 验证码工具类
 */
public class ValidateCodeUtil {

    /**
     * 获得几位数的验证码
     * @param charCount
     * @return
     */
    public static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + 48);
            charValue = charValue + String.valueOf(c);
        }
        return charValue;
    }

    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
}
