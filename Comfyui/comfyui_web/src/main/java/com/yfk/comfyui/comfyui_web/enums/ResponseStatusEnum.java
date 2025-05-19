package com.yfk.comfyui.comfyui_web.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 14:20
 */

@NoArgsConstructor
@AllArgsConstructor
public enum ResponseStatusEnum {
    FAIL(-1, "fail"),
    SUCCESS(0, "success")
    ;
    public int code;
    public String message;
}
