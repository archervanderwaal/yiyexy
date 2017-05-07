package com.yiyexy.model.car;

import java.io.Serializable;
import java.util.Date;

public class CarInformation implements Comparable<CarInformation>, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 拼车信息 id
     */
    private int iid;

    /**
     * 用户 id
     */
    private int uid;

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
    private String startTime_min_hour;

    /**
     * 拼车下限时间分钟值ֵ
     */
    private String startTime_min_min;

    /**
     * 拼车上限时间小时值ֵ
     */
    private String startTime_max_hour;

    /**
     * 拼车上限时间分钟值ֵ
     */
    private String startTime_max_min;

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

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
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

    public String getStartTime_min_hour() {
        return startTime_min_hour;
    }

    public void setStartTime_min_hour(String startTime_min_hour) {
        this.startTime_min_hour = startTime_min_hour;
    }

    public String getStartTime_min_min() {
        return startTime_min_min;
    }

    public void setStartTime_min_min(String startTime_min_min) {
        this.startTime_min_min = startTime_min_min;
    }

    public String getStartTime_max_hour() {
        return startTime_max_hour;
    }

    public void setStartTime_max_hour(String startTime_max_hour) {
        this.startTime_max_hour = startTime_max_hour;
    }

    public String getStartTime_max_min() {
        return startTime_max_min;
    }

    public void setStartTime_max_min(String startTime_max_min) {
        this.startTime_max_min = startTime_max_min;
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
                ", startTime_min_hour='" + startTime_min_hour + '\'' +
                ", startTime_min_min='" + startTime_min_min + '\'' +
                ", startTime_max_hour='" + startTime_max_hour + '\'' +
                ", startTime_max_min='" + startTime_max_min + '\'' +
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
