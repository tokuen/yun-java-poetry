package com.yun.springboot.model.result;

public enum ErrorCode {
    SUCCESS                                             (0, "success"),
    //10000000-10001000 登录异常
    ERR_CODE_NOT_LOGIN_10000000                         (-10000000, "没有登录"),
    ERR_CODE_PERMISSION_DENIED_10001001                 (-10000001, "没有权限"),
    //10001000-10002000 系统异常
    ERR_CODE_SYSTEM_UNKNOWN_ERROR_10001000              (-10001000, "系统未知异常"),
    ERR_CODE_NO_DATA_FOUND_10001001                     (-10001001, "未查询到数据"),
    ERR_CODE_PARSE_JSON_DATA_ERROR_10001002             (-10001002, "解析json异常，请检查json格式"),
    //10002000-10003000 业务异常
    //10003000-10004000 活动异常
    //10005000-10006000

    //99999999自定义错误
    ERR_CODE_CUSTOM_ERROR_99999999                      (-99999999, "自定义错误，此内容可以被重写。配合自定义异常使用");

    public int code;
    public String message;

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    }