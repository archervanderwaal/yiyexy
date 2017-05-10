package com.yiyexy.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * <p>Created on 2017/5/10.</p>
 *
 * @author stormma
 *
 * @description: reids 工具类
 */
@Component
public class StringRedisTempldateUtil {

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    public RedisTemplate<String, String> getStringRedisTempldate() {
        return redisTemplate;
    }
}
