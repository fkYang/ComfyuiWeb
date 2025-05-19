package com.yfk.comfyui.comfyui_common.enums;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 16:55
 */
public enum FlowInputTypeEnum implements CodeEnum<Integer> {
    TEXT(0, "text"),
    IMAGE(1, "image");
    private int code;
    private String desc;

    FlowInputTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;

    }

    @Override
    public Integer getCode() {
        return code;
    }
}