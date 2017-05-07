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
}
