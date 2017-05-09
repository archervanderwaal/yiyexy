package com.yiyexy.dao.car;

import com.yiyexy.model.car.Member;
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
 * @description: 成员测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberDaoTest {

    @Autowired
    private MemberDao memberDao;

    private static final Logger LOGGER = MyLogger.getLogger(MemberDaoTest.class);

    @Test
    public void testGetMember() {
        int iid = 293;

        Member member = memberDao.getMember(iid);

        LOGGER.info("测试结果:{}", member);
    }

    @Test
    public void testAddUserToMember() {
        Member member = new Member();
        member.setIid(293);
        member.setUid3(56);

        memberDao.addUserToMember(member);
    }
}
