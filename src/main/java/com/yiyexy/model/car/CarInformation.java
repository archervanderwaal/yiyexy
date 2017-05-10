package com.yiyexy.model.car;

import java.io.Serializable;
import java.util.Date;

public class CarInformation implements Comparable<CarInformation>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 拼车信息 id
     */
    private Integer iid;

    /**
     * 用户 id
     */
    private Integer uid;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 开始地点
     */
    private String startPos;

    /**
     * 目的地
     */
    private String arrivePos;

    /**
     * 拼车下限时间小时值ֵ
     */
    private String startTimeMinHour;

    /**
     * 拼车下限时间分钟值ֵ
     */
    private String startTimeMinMin;

    /**
     * 拼车上限时间小时值ֵ
     */
    private String startTimeMaxHour;

    /**
     * 拼车上限时间分钟值ֵ
     */
    private String startTimeMaxMin;

    /**
     * 拼车最大人数
     */
    private int maxMember;

    /**
     * 拼车已有人数
     */
    private int curtMember;

    /**
     * 拼车信息备注
     */
    private String message;

    /**
     * 拼车发布日期
     */
    private Date pubTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStartPos() {
        return startPos;
    }

    public void setStartPos(String startPos) {
        this.startPos = startPos;
    }

    public String getArrivePos() {
        return arrivePos;
    }

    public void setArrivePos(String arrivePos) {
        this.arrivePos = arrivePos;
    }

    public String getStartTimeMinHour() {
        return startTimeMinHour;
    }

    public void setStartTimeMinHour(String startTimeMinHour) {
        this.startTimeMinHour = startTimeMinHour;
    }

    public String getStartTimeMinMin() {
        return startTimeMinMin;
    }

    public void setStartTimeMinMin(String startTimeMinMin) {
        this.startTimeMinMin = startTimeMinMin;
    }

    public String getStartTimeMaxHour() {
        return startTimeMaxHour;
    }

    public void setStartTimeMaxHour(String startTimeMaxHour) {
        this.startTimeMaxHour = startTimeMaxHour;
    }

    public String getStartTimeMaxMin() {
        return startTimeMaxMin;
    }

    public void setStartTimeMaxMin(String startTimeMaxMin) {
        this.startTimeMaxMin = startTimeMaxMin;
    }

    public int getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(int maxMember) {
        this.maxMember = maxMember;
    }

    public int getCurtMember() {
        return curtMember;
    }

    public void setCurtMember(int curtMember) {
        this.curtMember = curtMember;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    @Override
    public String toString() {
        return "CarInformation{" +
                "iid=" + iid +
                ", uid=" + uid +
                ", startDate=" + startDate +
                ", startPos='" + startPos + '\'' +
                ", arrivePos='" + arrivePos + '\'' +
                ", startTimeMinHour='" + startTimeMinHour + '\'' +
                ", startTimeMinMin='" + startTimeMinMin + '\'' +
                ", startTimeMaxHour='" + startTimeMaxHour + '\'' +
                ", startTimeMaxMin='" + startTimeMaxMin + '\'' +
                ", maxMember=" + maxMember +
                ", curtMember=" + curtMember +
                ", message='" + message + '\'' +
                ", pubTime=" + pubTime +
                '}';
    }

    @Override
    public int compareTo(CarInformation o) {
        if (this.getStartDate().getTime() > o.getStartDate().getTime()) {
            return -1;
        } else if (this.getStartDate().getTime() < o.getStartDate().getTime()) {
            return 1;
        } else {
            return 0;
        }
    }
}
