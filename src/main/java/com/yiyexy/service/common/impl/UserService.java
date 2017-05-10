package com.yiyexy.service.common.impl;

import com.yiyexy.constant.CommonConstant;
import com.yiyexy.constant.DBConstant;
import com.yiyexy.constant.UserConstant;
import com.yiyexy.constant.ValidateConstant;
import com.yiyexy.dao.common.UserDao;
import com.yiyexy.model.common.User;
import com.yiyexy.service.common.IMessageService;
import com.yiyexy.service.common.IUserService;
import com.yiyexy.util.EncryptionUtil;
import com.yiyexy.util.ObjectUtil;
import com.yiyexy.util.RegexpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private IMessageService messageService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }

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

    /**
     * 修改密码
     * @param mobile
     * @param password
     * @return
     */
    @Override
    public boolean updatePassword(String mobile, String password) {

        User paramUser = new User();
        paramUser.setMobile(mobile);
        //md5加密
        paramUser.setPassword(EncryptionUtil.md5Encryption(password));
        userDao.updatePassword(paramUser);
        return true;
    }

    /**
     * 修改qq
     * @param mobile
     * @param qq
     * @return
     */
    @Override
    public boolean updateQQNum(String mobile, String qq) {
        User paramUser = new User();

        paramUser.setMobile(mobile);
        paramUser.setQq(qq);
        userDao.updateQQ(paramUser);

        return true;
    }

    /**
     * 修改用户名
     * @param mobile
     * @param userName
     * @return
     */
    @Override
    public String updateUserName(String mobile, String userName) {
        User paramUser = new User();

        paramUser.setMobile(mobile);
        paramUser.setUserName(userName);

        int count = userDao.getUpdatePwdCount(mobile);
        if (count >= 1) {
            return UserConstant.CAN_NOT_UPDATE_USER_NAME;
        }
        userDao.updateUserName(paramUser);
        return CommonConstant.SUCCESS;
    }

    /**
     * 注册时候发送验证码
     * @param moible
     * @return
     */
    @Override
    public Map<String, String> sendValidateCode(String moible, int type) {
        Map<String, String> datas = new HashMap<>();
        //正则匹配手机号码
        if (!RegexpUtil.isMobileNum(moible)) {
            datas.put(CommonConstant.FAIL, CommonConstant.INVALID_MOBILE_NUM);
            return datas;
        }
        //如果是注册时候发送验证码，先判断手机号码是否已经注册
        if (type == ValidateConstant.REGISTER_VALIDATE_CODE) {
            if (userDao.isExistMobile(moible) == DBConstant.HAVE_THIS_RECORD) {
                datas.put(CommonConstant.FAIL, ValidateConstant.THIS_MOBILE_ALERDY_REGISTER);
                return datas;
            }
        }
        //判断是否可以发送短信
        boolean isCanSend = messageService.isCanSendMessage(moible);
        if (isCanSend) {
            Map<String, String> result = messageService.sendMessage(moible);
            if (!ObjectUtil.isEmpty(result.get(CommonConstant.SUCCESS))) {
                //发送成功，保存验证码到 redis
                redisTemplate.boundValueOps(moible).set(result.get(CommonConstant.SUCCESS),
                        ValidateConstant.VALIDATE_CODE_VALID_TIME_MINUNTES, TimeUnit.MINUTES);
                datas.put(CommonConstant.SUCCESS, result.get(CommonConstant.SUCCESS));
                //增加发送次数
                messageService.increaseSendValidateCodeCount(moible);
            } else {
                datas.put(CommonConstant.FAIL, ValidateConstant.VALIDATE_SEND_FAIL);
            }
        } else {
            datas.put(CommonConstant.FAIL, ValidateConstant.VALIDATE_SEND_MAX_COUNT_CODE);
        }
        return datas;
    }

    /**
     * 注册
     * @param mobile
     * @param password
     * @return
     */
    @Override
    public Map<String, String> register(String mobile, String password) {
        Map<String, String> datas = new HashMap<>();
        //判断手机号码是否已经注册
        if (userDao.isExistMobile(mobile) == DBConstant.HAVE_THIS_RECORD) {
            datas.put(CommonConstant.FAIL, ValidateConstant.THIS_MOBILE_ALERDY_REGISTER);
            return datas;
        }
        userDao.insertUser(new User(password, mobile));
        datas.put(CommonConstant.SUCCESS, CommonConstant.SUCCESS);
        return datas;
    }
}
