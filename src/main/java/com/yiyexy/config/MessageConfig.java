package com.yiyexy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>Created on 2017/5/8.</p>
 *
 * @author stormma
 *
 * @description: 短信验证码配置类
 */
@Component
@ConfigurationProperties(locations = {"classpath:config/message.properties"}, prefix = "message")
public class MessageConfig {

    /**
     * url
     */
    private String url;

    /**
     * userName
     */
    private String username;

    /**
     * password
     */
    private String password;

    /**
     * 是否返回结果码
     */
    private String rd;

    /**
     * 验证码位数
     */
    private int validateCodeCount;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public int getValidateCodeCount() {
        return validateCodeCount;
    }

    public void setValidateCodeCount(int validateCodeCount) {
        this.validateCodeCount = validateCodeCount;
    }

    @Override
    public String toString() {
        return "MessageConfig{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rd='" + rd + '\'' +
                ", VALIDATE_CODE_COUNT=" + validateCodeCount +
                '}';
    }
}
