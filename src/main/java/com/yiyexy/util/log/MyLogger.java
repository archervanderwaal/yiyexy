package com.yiyexy.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Created on 2017/5/8.</p>
 *
 * @author stormma
 *
 * @descritpion: 日志记录器
 */
public class MyLogger {

    /**
     * 获得日志记录器
     * @param clazz
     * @return
     */
    public static Logger getLogger(Class clazz) {

        return LoggerFactory.getLogger(clazz);
    }
}
