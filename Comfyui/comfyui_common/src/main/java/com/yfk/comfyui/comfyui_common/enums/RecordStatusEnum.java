package com.yfk.comfyui.comfyui_common.enums;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 18:11
 */
public enum RecordStatusEnum implements CodeEnum<Integer> {
    WAITING(0, "等待执行结果"),
    DONE(1, "执行完成");
    private int code;
    private String desc;

    RecordStatusEnum( int code, String desc){
        this.code = code;
        this.desc = desc;

    }
    @Override
    public Integer getCode () {
        return code;
    }
}