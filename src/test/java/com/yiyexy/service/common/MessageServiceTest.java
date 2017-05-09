package com.yiyexy.service.common;

import com.yiyexy.constant.CommonConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * <p>Created on 2017/5/8.</p>
 *
 * @author stormma
 *
 * @description: 验证码测试接口
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private IMessageService messageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceTest.class);

    @Test
    public void testSendMessage() {
        String mobile = "18292817803";

        Map<String, String> datas = messageService.sendMessage(mobile);

        if (datas.get(CommonConstant.SUCCESS) != null) {
            LOGGER.info("验证码为:{}", datas.get(CommonConstant.SUCCESS));
        }
    }
}
