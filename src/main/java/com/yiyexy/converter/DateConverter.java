package com.yiyexy.converter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p> Create on 2017/5/7.</p>
 *
 * @author stormma
 *
 * @description: <p>日期转换器</p>
 */
public class DateConverter implements Converter<String, Date> {
    private Logger logger = LoggerFactory.getLogger(DateConverter.class);

    @Override
    public Date convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        try {
            long milltime = Long.valueOf(source);
            return new Date(milltime);
        } catch (Exception e){
            logger.warn("传入的时间参数不是Long类型, 按String类型处理");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(source);
        } catch (ParseException e) {
            logger.error("Date转换错误: str=" + source + ", errorMsg=" + e.getMessage());
        }
        return null;
    }
}
