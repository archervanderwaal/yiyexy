package com.yiyexy.manager.impl;

import com.yiyexy.constant.TokenConstant;
import com.yiyexy.manager.TokenManager;
import com.yiyexy.model.common.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 通过Redis存储和验证token的实现类
 * @author ScienJus
 * @date 2015/7/31.
 */
@Component
public class RedisTokenManager implements TokenManager {

    private RedisTemplate<Integer, String> redisTemplate;

    @Autowired
    private void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }
    /**
     * 创建 token
     * @param userId
     * @return
     */
    @Override
    public TokenModel createToken(Integer userId) {
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel model = new TokenModel(userId, token);
        redisTemplate.boundValueOps(userId).set(token, TokenConstant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return model;
    }

    /**
     * 获取 token
     * @param authentication 加密后的字符串
     * @return
     */
    @Override
    public TokenModel getToken(String authentication) {
        if (authentication == null || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if (param.length != 2) {
            return null;
        }
        //使用userId和源token简单拼接成的token，可以增加加密措施
        Integer userId = Integer.parseInt(param[0]);
        String token = param[1];
        return new TokenModel(userId, token);
    }

    /**
     * 检查 token 的有效性
     * @param model token
     * @return
     */
    @Override
    public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = redisTemplate.boundValueOps(model.getUid()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redisTemplate.boundValueOps(model.getUid()).expire(TokenConstant.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }

    /**
     * 删除用户的 token 信息
     * @param userId 登录用户的id
     */
    @Override
    public void deleteToken(Integer userId) {
        redisTemplate.delete(userId);
    }
}
