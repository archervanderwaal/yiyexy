package com.yiyexy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Created on 2017/5/7.</p>
 *
 * @author stormma
 *
 * @description: <p>加密工具类</p>
 */
public class EncryptionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptionUtil.class);

    public static String md5Encryption(String source) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("加密出错:{}", e);
        }
        byte [] md5 = md.digest(source.getBytes());

        BASE64Encoder encode = new BASE64Encoder();

        return encode.encode(md5);
    }
}
