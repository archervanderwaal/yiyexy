package com.yiyexy.model.common;

/**
 * <p>Create on 2017/5/7</p>
 *
 * @author stormma
 *
 * @description: <p>token 的 model 类</p>
 */
public class TokenModel {

    /**
     * 用户 id
     */
    private Integer uid;

    /**
     * 随机生成 token
     */
    private String token;

    public TokenModel() {
    }

    public TokenModel(Integer uid, String token) {
        this.uid = uid;
        this.token = token;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "uid=" + uid +
                ", token='" + token + '\'' +
                '}';
    }
}
