package com.koowakchai.store.common.config.base;

public class ResponseResult {
    private Integer result;//200正常 500异常

    private String message;

    private Object data;

    public ResponseResult() {
    }

    public ResponseResult(Integer result, String message, Object data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(Integer result, Object data) {
        this.result = result;
        this.data = data;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
