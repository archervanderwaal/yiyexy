package com.yiyexy.service.car.impl;

import com.yiyexy.constant.CarInformationConstant;
import com.yiyexy.constant.CommonConstant;
import com.yiyexy.constant.MemberConstant;
import com.yiyexy.dao.car.CarInformationDao;
import com.yiyexy.dao.car.MemberDao;
import com.yiyexy.dao.common.UserDao;
import com.yiyexy.model.car.CarInformation;
import com.yiyexy.model.car.Member;
import com.yiyexy.model.common.User;
import com.yiyexy.service.car.ICarInformationService;
import com.yiyexy.service.car.IMemberService;
import com.yiyexy.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

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

    @Autowired
    private ICarInformationService carInformationService;

    /**
     * 取消用户uid在iid拼车的行程信息
     *
     * @param iid
     * @param uid
     * @return
     */
    @Override
    @Transactional
    public Map<String, String> cancelStroke(int iid, int uid) {

        Map<String, String> datas = new HashMap<>();

        //首先判断要取消的这个用户是否加入了拼车该拼车信息如果是，继续操作，反之无权限
        if (!this.isSignedCarInformation(uid, iid)) {
            datas.put(CommonConstant.FAIL, MemberConstant.NO_SIGN_UP_THIS_CAR_INFORMATION);
            return datas;
        }
        //先查询iid这个拼车信息的成员
        Member member = memberDao.getMember(iid);
        Member param = this.conversionMember(member, uid);
        assert param != null;
        param.setIid(iid);
        memberDao.removeUserFromMember(param);
        if (param.getUid1() != null) {
            //删除拼车信息
            carInformationDao.removeCarInformation(iid);
        }
        datas.put(CommonConstant.SUCCESS, CommonConstant.SUCCESS);
        return datas;
    }

    /**
     * 根据拼车信息查询所有成员的信息
     *
     * @param iid
     * @return
     */
    @Override
    public List<User> getMemberInfos(int iid) {
        return userDao.getUserByIid(iid);
    }

    /**
     * 添加用户uid到行程中去
     *
     * @param iid
     * @param uid
     */
    @Override
    public Map<String, String> addUserToStroke(int iid, int uid) {

        Map<String, String> datas = new HashMap<>();
        Member member = memberDao.getMember(iid);
        if (ObjectUtil.isEmpty(member)) {
            datas.put(CommonConstant.FAIL, CarInformationConstant.NO_THIS_IID_CAR_INFORMATION);
            return datas;
        }
        Member param = new Member();
        //此处应该判断用户是否已经加入拼车信息
        if (this.isSignedCarInformation(uid, iid)) {
            datas.put(CommonConstant.FAIL, MemberConstant.ALERDY_SIGN_UP_CAR_INFORMATION);
            return datas;
        }
        //判断是否符合加入规则,同一天不能从同一地点出发两次
        if (!ObjectUtil.isEmpty(this.isCanSignUpCarInformation(uid, iid).get(CommonConstant.FAIL))) {
            datas.put(CommonConstant.FAIL, this.isCanSignUpCarInformation(uid, iid).get(CommonConstant.FAIL));
            return datas;
        }
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
            datas.put(CommonConstant.FAIL, MemberConstant.MEMBER_IS_FULL);
            return datas;
        }
        memberDao.addUserToMember(param);
        datas.put(CommonConstant.SUCCESS, CommonConstant.SUCCESS);
        return datas;
    }

    /**
     * 判断用户是否已经加入拼车信息
     *
     * @param uid
     * @param iid
     * @return
     */
    private boolean isSignedCarInformation(int uid, int iid) {
        Member member = memberDao.getMember(iid);
        if (Objects.equals(member.getUid1(), uid)) {
            return true;
        } else if (member.getUid2() == uid) {
            return true;
        } else if (member.getUid3() == uid) {
            return true;
        } else if (member.getUid4() == uid) {
            return true;
        } else if (member.getUid5() == uid) {
            return true;
        } else if (member.getUid6() == uid) {
            return true;
        }
        return false;
    }

    /**
     * 格式参数
     *
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

    /**
     * 判断uid是否可以报名iid这个行程信息(同一天不能从同一地点出发两次)
     * @param uid
     * @param iid
     * @return
     */
    @Override
    public Map<String, String> isCanSignUpCarInformation(int uid, int iid) {
        Map<String, String> datas = new HashMap<>();

        //查询用户已经有的行程信息
        List<CarInformation> carInformations = carInformationService.getUserSignedUpCarInformation(uid);
        //查询要报名的拼车信息
        CarInformation carInformation = carInformationDao.getCarInformation(iid);

        Date startDate = carInformation.getStartDate();
        String startPos = carInformation.getStartPos();

        if (!CollectionUtils.isEmpty(carInformations)) {
            for (CarInformation information : carInformations) {
                //如果出发日期是同一天并且出发地点还是相同的，那么不符合报名的规则
                if (information.getStartDate().compareTo(startDate) == 0) {
                    if (information.getStartPos().equals(startPos)) {
                        datas.put(CommonConstant.FAIL, MemberConstant.INVAILD_SIGN_UP);
                        return datas;
                    }
                }
            }
        }
        datas.put(CommonConstant.SUCCESS, CommonConstant.SUCCESS);
        return datas;
    }
}
