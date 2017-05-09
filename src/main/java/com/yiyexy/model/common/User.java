package com.yiyexy.model.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * <p>Created on 2017/5/7.</p>
 *
 * @author stormma
 *
 * @description: <p>user model of yiyexy</p>
 */
public class User {

    /**
     * user id
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer uid;

    /**
     * icon
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String icon;

    /**
     * qq num
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String qq;

    /**
     * user name
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String userName;

    /**
     * password
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String password;

    /**
     * mobile
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String mobile;

    /**
     * good apprise
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer goodApprise;

    /**
     * bad apprise
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer badApprise;

    /**
     * update password count
     */
    @JsonIgnore
    private Integer updatePwdCount;

    /**
     * type
     */
    @JsonIgnore
    private Integer type;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getGoodApprise() {
        return goodApprise;
    }

    public void setGoodApprise(Integer goodApprise) {
        this.goodApprise = goodApprise;
    }

    public Integer getBadApprise() {
        return badApprise;
    }

    public void setBadApprise(Integer badApprise) {
        this.badApprise = badApprise;
    }

    public Integer getUpdatePwdCount() {
        return updatePwdCount;
    }

    public void setUpdatePwdCount(Integer updatePwdCount) {
        this.updatePwdCount = updatePwdCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", icon='" + icon + '\'' +
                ", qq='" + qq + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", goodApprise=" + goodApprise +
                ", badApprise=" + badApprise +
                ", updatePwdCount=" + updatePwdCount +
                ", type=" + type +
                '}';
    }
}
