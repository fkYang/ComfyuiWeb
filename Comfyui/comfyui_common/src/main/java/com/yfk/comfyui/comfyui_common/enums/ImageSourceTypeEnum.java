package com.yfk.comfyui.comfyui_common.enums;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 16:10
 */
public enum ImageSourceTypeEnum implements CodeEnum<Integer> {
    USER_UPLOAD(0, "1"),
    COMFYUI_GEN(1, "1");
    private int code;
    private String desc;

    ImageSourceTypeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;

    }
    @Override
    public Integer getCode() { return code; }

}
