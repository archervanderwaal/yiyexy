package com.yiyexy.util.result;

/**
 * <p>Created on 2017/3/15.</p>
 *
 * @author StormMa
 *
 * @Description: 封装请求结果的工具类
 */
public class ResultBuilder {

    /**
     * 成功请求的结果封装
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T t) {
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMsg("success");
        result.setData(t);
        return result;
    }

    /**
     * 成功请求的结果封装
     * @param <T>
     * @return
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 失败请求的结果封装
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setCode(1);
        result.setData(null);
        result.setMsg("fail");
        return result;
    }

    /**
     * 失败请求的结果封装
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(String msg) {
        Result<T> result = new Result<>();
        result.setCode(1);
        result.setData(null);
        result.setMsg(msg);
        return result;
    }
}
