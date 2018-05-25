package com.example.template.entity.pojo;

import java.io.Serializable;

/**
 * 返回操作结果和响应数据
 *
 * @param <T>
 */
public class ResponseResult<T> implements Serializable {

    private String success;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(Result result) {
        this.success = result.getMsg();
    }

    public ResponseResult(Result result, T data) {
        this.success = result.getMsg();
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "success='" + success + '\'' +
                ", data=" + data +
                '}';
    }
}
