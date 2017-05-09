package com.yiyexy.util;

import com.yiyexy.constant.ValidateConstant;
import com.yiyexy.util.log.MyLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * <p>Created on 2017/5/9.</p>
 *
 * @author stormma
 *
 * @description: 判断
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {

    private RedisTemplate<String, Integer> redisTemplate;

    private static final Logger LOGGER = MyLogger.getLogger(RedisTemplateTest.class);
    @Autowired
    private void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    @Test
    public void testGetSendValidareCodeCount() {

        String mobile = "18292817803";
        String key = mobile + ValidateConstant.VALIDATE_REDIS_SUFFIX;

        redisTemplate.boundValueOps(key).set(10, redisTemplate.boundValueOps(key).getExpire(), TimeUnit.SECONDS);
        long day = redisTemplate.boundValueOps(key).getExpire();

        LOGGER.info("{}", day);
    }
}
