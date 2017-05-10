package com.yiyexy.model.car;

/**
 * <p>Created on 2017/5/10.</p>
 *
 * @author stormma
 *
 * @description: 拼车反馈
 */
public class CarRetroaction {

    /**
     * uid
     */
    private Integer uid;

    /**
     * 拼车信息 id
     */
    private Integer iid;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 是否成功(成功或者失败)
     */
    private String status;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CarRetroaction{" +
                "uid=" + uid +
                ", iid=" + iid +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
