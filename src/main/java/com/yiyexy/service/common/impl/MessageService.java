package com.yiyexy.service.common.impl;

import com.yiyexy.annotation.Authorization;
import com.yiyexy.config.MessageConfig;
import com.yiyexy.constant.CommonConstant;
import com.yiyexy.constant.ValidateConstant;
import com.yiyexy.sender.MessageSender;
import com.yiyexy.service.common.IMessageService;
import com.yiyexy.util.ObjectUtil;
import com.yiyexy.util.StringRedisTempldateUtil;
import com.yiyexy.util.ValidateCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>Created on 2017/5/8.</p>
 *
 * @author stormma
 *
 * @description: 短信验证码服务
 */
@Service
public class MessageService implements IMessageService {

    @Autowired
    private MessageConfig messageConfig;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private StringRedisTempldateUtil stringRedisTempldateUtil;

    private RedisTemplate<String, Integer> redisTemplate;

    private static Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    /**
     * 发送验证码服务，前提要判断是否具有权限
     * @param mobile
     * @return
     */
    @Override
    public Map<String, String> sendMessage(String mobile) {

        //发送的内容
        String msg = null;
        //生成验证码
        String validateCode = ValidateCodeUtil.getRandNum(messageConfig.getValidateCodeCount());

        //格式化发送内容
        msg = String.format(CommonConstant.MESSAGE_TEMPLATE, validateCode);

        String returnResult;
        try {
            returnResult = messageSender.batchSend(messageConfig.getUrl()
                                    , messageConfig.getUsername()
                                    , messageConfig.getPassword()
                                    , mobile
                                    , msg
                                    , messageConfig.getRd()
                                    , null);

            LOGGER.info("返回字符串:{}", returnResult);
            Map<String, String> datas = new HashMap<>();
            //成功时候返回生成的验证码
            datas.put(CommonConstant.SUCCESS, validateCode);
            return datas;
        } catch (Exception e) {
            LOGGER.error("{}", e);
            Map<String, String> datas = new HashMap<>();
            datas.put(CommonConstant.FAIL, e.toString());
            return datas;
        }
    }

    /**
     * 判断是否可以发送短信验证码
     * @param mobile
     * @return
     */
    @Override
    public boolean isCanSendMessage(String mobile) {
        //获得该手机号码一周之内发送的验证码的数目
        Integer count = this.getSendValidateCodeCountOneWeek(mobile);
        if (ObjectUtil.isEmpty(count)) {
            return true;
        } else {
            return count < CommonConstant.SEND_MAX_VALIDATE_CODE_ONE_WEEK;
        }
    }

    /**
     * 增加发送短信验证码的数目
     * @param mobile
     */
    @Override
    public void increaseSendValidateCodeCount(String mobile) {
        Integer count = this.getSendValidateCodeCountOneWeek(mobile);

        String key = this.getRedisKeyForSendValidateCodeCount(mobile);
        if (ObjectUtil.isEmpty(count)) {
            redisTemplate.boundValueOps(key).set(1, ValidateConstant.VALIDATE_CHECK_DAYS, TimeUnit.DAYS);
        } else {
            //增加1，继续设置上次的失效时间
            redisTemplate.boundValueOps(key).set(count + 1, redisTemplate.boundValueOps(key).getExpire(), TimeUnit.SECONDS);
        }
    }

    /**
     * 获得保存发送验证码数目的key
     * @param mobile
     * @return
     */
    @Override
    public String getRedisKeyForSendValidateCodeCount(String mobile) {
        return mobile + ValidateConstant.VALIDATE_REDIS_SUFFIX;
    }

    /**
     * 获得该手机号码一周发送的数目
     * @param mobile
     * @return
     */
    @Override
    public Integer getSendValidateCodeCountOneWeek(String mobile) {

        String key = this.getRedisKeyForSendValidateCodeCount(mobile);
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     * 判断该手机号提交的验证码是否正确
     * @param validateCode
     * @param mobile
     * @return
     */
    @Override
    public boolean checkValidateIsValid(String validateCode, String mobile) {

        //redis 取出缓存
        String originValidateCode = stringRedisTempldateUtil.getStringRedisTempldate().boundValueOps(mobile).get();

        //如果相等
        if (StringUtils.isEmpty(originValidateCode) || !originValidateCode.trim().equals(validateCode.trim())) {
             return false;
        }
        return true;
    }
}
