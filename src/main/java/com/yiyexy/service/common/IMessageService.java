package com.yiyexy.service.common;

import java.util.Map;

/**
 * <p>Created on 2017/5/8.</p>
 *
 * @author stormma
 *
 * @description: 短信验证码服务
 */
public interface IMessageService {

    /**
     * 发送验证码服务，前提要判断是否具有权限
     * @param mobile
     * @return
     */
    Map<String, String> sendMessage(String mobile);

    /**
     * 判断是否可以发送短信验证码
     * @param mobile
     * @return
     */
    boolean isCanSendMessage(String mobile);

    /**
     * 增加发送短信验证码的数目
     * @param mobile
     */
    void increaseSendValidateCodeCount(String mobile);

    /**
     * 获得保存发送验证码数目的key
     * @param mobile
     * @return
     */
    String getRedisKeyForSendValidateCodeCount(String mobile);

    /**
     * 获得该手机号码一周发送的数目
     * @param mobile
     * @return
     */
    Integer getSendValidateCodeCountOneWeek(String mobile);

    /**
     * 判断该手机号提交的验证码是否正确
     * @param validateCode
     * @param mobile
     * @return
     */
    boolean checkValidateIsValid(String validateCode, String mobile);
}
