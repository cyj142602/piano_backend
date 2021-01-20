package com.cyj.piano_backend.bean.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonResult<T> {

    private int code;
    private String msg;
    private T info;

    public JsonResult() {
    }

    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(int code, String msg, T info) {
        this.code = code;
        this.msg = msg;
        this.info = info;
    }

    //查询成功
    public static <T> JsonResult<T> cx_success(T info) {
        return new JsonResult<>(200, "获取数据成功", info);
    }

    //保存成功
    public static <T> JsonResult<T> bc_success(T info) {
        return new JsonResult<>(200, "数据提交成功", info);
    }

    //查询失败
    public static <T> JsonResult<T> cx_fail(T info) {
        return new JsonResult<>(200, "获取数据失败", info);
    }

    //保存失败
    public static <T> JsonResult<T> bc_fail(T info) {
        return new JsonResult<>(200, "数据提交失败", info);
    }
}
