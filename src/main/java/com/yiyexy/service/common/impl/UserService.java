package com.yiyexy.service.common.impl;

import com.yiyexy.constant.CommonConstant;
import com.yiyexy.constant.DBConstant;
import com.yiyexy.constant.UserConstant;
import com.yiyexy.dao.common.UserDao;
import com.yiyexy.model.common.User;
import com.yiyexy.service.common.IUserService;
import com.yiyexy.util.EncryptionUtil;
import com.yiyexy.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Created on 2017/5/7.</p>
 *
 * @author stormma
 *
 * @description: <p>用户服务类实现</p>
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    /**
     * 判断是否登录成功
     * @param mobile
     * @param password
     * @return
     */
    @Override
    public Map<String, Integer> isLoginSuccess(String mobile, String password) {
        User paramUser = new User();
        paramUser.setMobile(mobile);
        paramUser.setPassword(EncryptionUtil.md5Encryption(password));
        Integer uid = userDao.getUidByMobileAndPassword(paramUser);
        Map<String, Integer> datas = new HashMap<>();
        //登录失败
        if (ObjectUtil.isEmpty(uid)) {
            int status;
            //如果手机号未注册
            if (userDao.isExistMobile(mobile) == DBConstant.NO_THIS_RECORD) {
                status = UserConstant.NO_THIS_MOBILE;
            } else {
                status = UserConstant.ERROR_PASSWORD;
            }
            datas.put(CommonConstant.FAIL, status);
        } else {
            datas.put(CommonConstant.SUCCESS, uid);
        }
        return datas;
    }
}
