package com.yiyexy.dao.car;

import com.yiyexy.dao.common.UserDao;
import com.yiyexy.model.common.User;
import com.yiyexy.util.EncryptionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>Created on 2017/5/7.</p>
 *
 * @author stormma
 *
 * @description: <p>用户dao层接口测试用例</p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);

    @Test
    public void testGetUser() {

        User user = new User();

        user. setMobile("18292817803");
        user.setPassword(EncryptionUtil.md5Encryption("StormMa"));

        User result = userDao.getUser(user);

        LOGGER.info("测试结果:{}", result);
    }
}
