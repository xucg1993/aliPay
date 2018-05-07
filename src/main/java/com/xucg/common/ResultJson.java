package com.xucg.common;

import com.alibaba.fastjson.JSONObject;

/**
 * 返回结果
 *
 * @author xuchenguang
 * @date 2018/4/3
 */
public class ResultJson {
    //调用成功
    public static final Boolean SUCCESS_TRUE = true;
    //调用失败
    public static final Boolean SUCCESS_FALSE = false;

    //状态码
    public static final Integer CODE_1 = 1001;
    public static final Integer CODE_2 = 1002;
    public static final Integer CODE_3 = 1003;
    public static final Integer CODE_4 = 1004;
    public static final Integer CODE_5 = 1005;
    public static final Integer CODE_6 = 1006;
    public static final Integer CODE_7 = 1007;
    public static final Integer CODE_8 = 1008;
    public static final Integer CODE_9 = 1009;
    public static final Integer CODE_10 = 1010;
    public static final Integer CODE_0 = 1000;

    //返回
    public static final String MSG_SUCCESS = "SUCCESS";
    public static final String MSG_FAIL = "FAIL";
    public static final String MSG_ERROR = "ERROR";
    public static final String EMPTY_STRING = "";

    private boolean success;
    private Integer code;
    private Object message;
    private Object info;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    /**
     * 自定义
     *
     * @param success
     * @param code
     * @param message
     * @param info
     * @return
     */
    public static String getResultJson(Boolean success, Integer code, Object message, Object info) {
        return JSONObject.toJSONString(buildResult(success, code, message, info));
    }

    /**
     * 调用成功
     * 成功 自定义
     *
     * @return
     */
    public static String getResultJsonSuccess() {
        return JSONObject.toJSONString(buildResult(SUCCESS_TRUE, CODE_1, MSG_SUCCESS, EMPTY_STRING));
    }

    public static String getResultJsonSuccess(Object info) {
        return JSONObject.toJSONString(buildResult(SUCCESS_TRUE, CODE_1, MSG_SUCCESS, info));
    }

    public static String getResultJsonSuccess(Integer code, Object info) {
        return JSONObject.toJSONString(buildResult(SUCCESS_TRUE, code, MSG_SUCCESS, info));
    }

    public static String getResultJsonSuccess(Integer code, Object message, Object info) {
        return JSONObject.toJSONString(buildResult(SUCCESS_TRUE, code, message, info));
    }

    /**
     * 调用成功
     * 返回失败 自定义
     *
     * @return
     */
    public static String getResultJsonFail() {
        return JSONObject.toJSONString(buildResult(SUCCESS_TRUE, CODE_2, MSG_FAIL, EMPTY_STRING));
    }

    public static String getResultJsonFail(Object info) {
        return JSONObject.toJSONString(buildResult(SUCCESS_TRUE, CODE_2, MSG_FAIL, info));
    }

    public static String getResultJsonFail(Integer code, Object info) {
        return JSONObject.toJSONString(buildResult(SUCCESS_TRUE, code, MSG_FAIL, info));
    }

    /**
     * 调用失败
     * 返回系统错误
     *
     * @return
     */
    public static String getResultJsonError() {
        return JSONObject.toJSONString(buildResult(SUCCESS_FALSE, CODE_0, MSG_ERROR, EMPTY_STRING));
    }


    /**
     * build实体类
     *
     * @param success
     * @param code
     * @param message
     * @param info
     * @return
     */
    public static ResultJson buildResult(Boolean success, Integer code, Object message, Object info) {
        ResultJson resultJson = new ResultJson();
        resultJson.setSuccess(success);
        resultJson.setCode(code);
        resultJson.setMessage(message);
        resultJson.setInfo(info);
        return resultJson;
    }
}
