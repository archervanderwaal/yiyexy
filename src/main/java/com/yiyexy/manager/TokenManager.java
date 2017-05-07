package com.yiyexy.manager;


import com.yiyexy.model.common.TokenModel;

/**
 * <p>Create on 2017/5/7</p>
 *
 * @author stormma
 *
 * @description: <p>对Token进行操作的接口</p>
 */
public interface TokenManager {

    /**
     * <p>创建一个token关联上指定用户</p>
     * @param userId
     * @return 生成的token
     */
    TokenModel createToken(Integer userId);

    /**
     * <p>检查token是否有效</p>
     * @param model token
     * @return 是否有效
     */
    boolean checkToken(TokenModel model);

    /**
     * <p>从字符串中解析token</p>
     * @param authentication 加密后的字符串
     * @return
     */
    TokenModel getToken(String authentication);

    /**
     * <p>清除token</p>
     * @param userId 登录用户的id
     */
    void deleteToken(Integer userId);

}
