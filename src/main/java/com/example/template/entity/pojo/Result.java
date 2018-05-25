package com.example.template.entity.pojo;

public enum Result {

    OK("恭喜您，操作成功!"),
    ERROR("操作失败，请稍后重试!"),
    EXISTED("该样板已存在"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED"),
    NOT_FOUND_404("请求错误"),
    VALIDFAIED("参数校验失败");

    private String msg;

    private Result(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
