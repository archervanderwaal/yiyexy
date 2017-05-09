package com.yiyexy.service.car.impl;

import com.yiyexy.dao.car.CarInformationDao;
import com.yiyexy.dao.car.MemberDao;
import com.yiyexy.dao.common.UserDao;
import com.yiyexy.exception.MemberUserIsFullException;
import com.yiyexy.model.car.Member;
import com.yiyexy.model.common.User;
import com.yiyexy.service.car.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Created on 2017/5/9.</p>
 *
 * @author stormma
 *
 * @description: 成员服务实现类
 */
@Service
public class MemberService implements IMemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CarInformationDao carInformationDao;

    /**
     * 取消用户uid在iid拼车的行程信息
     * @param iid
     * @param uid
     * @return
     */
    @Override
    @Transactional
    public boolean cancelStroke(int iid, int uid) {

        //先查询iid这个拼车信息的成员
        Member member = memberDao.getMember(iid);
        Member param = this.conversionMember(member, uid);
        if (param == null) {
            return false;
        }
        param.setIid(iid);
        memberDao.removeUserFromMember(param);
        if (param.getUid1() != null) {
            //删除拼车信息
            carInformationDao.removeCarInformation(iid);
        }
        return true;
    }

    /**
     * 根据拼车信息查询所有成员的信息
     * @param iid
     * @return
     */
    @Override
    public List<User> getMemberInfos(int iid) {
        return userDao.getUserByIid(iid);
    }

    /**
     * 添加用户uid到行程中去
     * @param iid
     * @param uid
     */
    @Override
    public void addUserToStroke(int iid, int uid) throws MemberUserIsFullException {
        Member member = memberDao.getMember(iid);
        Member param = new Member();
        param.setIid(iid);
        if (member.getUid2() == 0) {
            param.setUid2(uid);
        } else if (member.getUid3() == 0) {
            param.setUid3(uid);
        } else if (member.getUid4() == 0) {
            param.setUid4(uid);
        } else if (member.getUid5() == 0) {
            param.setUid5(uid);
        } else if (member.getUid6() == 0) {
            param.setUid6(uid);
        } else {
            throw new MemberUserIsFullException("成员已满");
        }
        memberDao.addUserToMember(param);
    }

    /**
     * 格式参数
     * @param member
     * @param uid
     * @return
     */
    private Member conversionMember(Member member, int uid) {
        Member param = new Member();
        //判断此时的uid是第几个用户
        //下面这个代码写的也没谁了，宛如小学生写的代码.但是这个之前的设计就有问题，再加上没有什么好的解决办法，继续沿用之前的设计算了，不然盖起来麻烦，
        //比赛的代码马上就要提交了，我只能赶时间啊， QAQ.....
        if (member.getUid1() == uid) {
            param.setUid1(uid);
        } else if (member.getUid2() == uid) {
            param.setUid2(uid);
        } else if (member.getUid3() == uid) {
            param.setUid3(uid);
        } else if (member.getUid4() == uid) {
            param.setUid4(uid);
        } else if (member.getUid5() == uid) {
            param.setUid5(uid);
        } else if (member.getUid6() == uid) {
            param.setUid6(uid);
        } else {
            return null;
        }
        return param;
    }
}
