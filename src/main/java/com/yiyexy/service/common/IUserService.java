package com.yiyexy.service.common;

import java.util.Map;

/**
 * <p>Created on 2017/5/7.</p>
 *
 * @author stormma
 *
 * @description: <p>用户服务类接口</p>
 */
public interface IUserService {

    /**
     * 判断是否登录成功
     * @param mobile
     * @param password
     * @return
     */
     Map<String, Integer> isLoginSuccess(String mobile, String password);

    /**
     * 修改密码
     * @param mobile
     * @param password
     * @return
     */
     boolean updatePassword(String mobile, String password);

    /**
     * 修改qq
     * @param mobile
     * @param qq
     * @return
     */
     boolean updateQQNum(String mobile, String qq);

    /**
     * 修改用户名
     * @param mobile
     * @param userName
     * @return
     */
     String updateUserName(String mobile, String userName);


    /**
     * 注册时候发送验证码
     * @param moible
     * @return
     */
    Map<String, String> sendValidateCode(String moible);
}
