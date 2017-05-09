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

import java.util.List;

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

    @Test
    public void testInsertUser() {

        User user = new User();

        user.setMobile("18292817801");
        user.setPassword(EncryptionUtil.md5Encryption("woaini"));

        userDao.insertUser(user);
        LOGGER.info("插入用户的id:{}", user.getUid());
    }

    @Test
    public void testIsExistUserName() {

        String userName = "StormMaybin";

        int result = userDao.isExistUserName(userName);

        LOGGER.info("测试结果:{}", result);
    }

    @Test
    public void testIsExistMobile() {

        String mobile = "18292817803";

        int result = userDao.isExistMobile(mobile);

        LOGGER.info("测试结果:{}", result);
    }

    @Test
    public void testUpdateUserName() {

        User user = new User();
        user.setUserName("StormMa");
        user.setMobile("18292817803");

        userDao.updateUserName(user);

        LOGGER.info("测试成功");
    }

    @Test
    public void testUpdatePassword() {

        User user = new User();
        user.setMobile("18292817803");
        user.setPassword(EncryptionUtil.md5Encryption("StormMa"));

        userDao.updatePassword(user);

        LOGGER.info("测试成功");
    }

    @Test
    public void testUpdateQQ() {

        User user = new User();

        user.setQq("1325338799");
        user.setMobile("18292817803");

        userDao.updateQQ(user);

        LOGGER.info("测试成功");
    }

    @Test
    public void testGetUpdatePwdCount() {

        String mobile = "18292817803";

        Integer result = userDao.getUpdatePwdCount(mobile);

        LOGGER.info("测试结果:{}", result);
    }

    @Test
    public void testGetUserByIid() {

        int iid = 293;

        List<User> users = userDao.getUserByIid(iid);

        LOGGER.info("测试结果:{}", users);
    }
}
