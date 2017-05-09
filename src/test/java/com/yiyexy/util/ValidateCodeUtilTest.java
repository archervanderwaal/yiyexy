package com.yiyexy.util;

import com.yiyexy.config.MessageConfig;
import com.yiyexy.util.log.MyLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Created on 2017/5/8.</p>
 *
 * @author stormma
 *
 * @description: 验证码生成器测试用例
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidateCodeUtilTest {

    @Autowired
    private MessageConfig messageConfig;

    private static final Logger LOGGER = MyLogger.getLogger(ValidateCodeUtilTest.class);

    @Test
    public void testGetCode() {
        String validateCode = ValidateCodeUtil.getRandNum(messageConfig.getValidateCodeCount());

        LOGGER.info("测试结果:{}", validateCode);
    }
}
