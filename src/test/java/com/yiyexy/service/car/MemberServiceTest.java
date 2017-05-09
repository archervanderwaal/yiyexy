package com.yiyexy.service.car;

import com.yiyexy.util.RegexpUtil;
import com.yiyexy.util.log.MyLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Created on 2017/5/9.</p>
 *
 * @author stormma
 *
 * @description 测试用例
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private IMemberService memberService;

    private static final Logger LOGGER = MyLogger.getLogger(MemberServiceTest.class);

    @Test
    public void testCancelStroke() {

        /*int iid = 293;

        int uid = 55;

        boolean result = memberService.cancelStroke(iid, uid);

        LOGGER.info("测试结果:{}", result);*/

        LOGGER.info("{}", RegexpUtil.isMobileNum("18292817803"));
    }
}
