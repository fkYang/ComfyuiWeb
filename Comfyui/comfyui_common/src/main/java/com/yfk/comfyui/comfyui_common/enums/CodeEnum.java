package com.yfk.comfyui.comfyui_common.enums;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 16:33
 */
public interface CodeEnum<T> {
    T getCode();

    static <E extends Enum<E> & CodeEnum<T>, T> E fromValue(Class<E> enumType, T code) {
        for (E value : enumType.getEnumConstants()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("无效的枚举编码: " + code + "，枚举类型: " + enumType.getSimpleName());
    }
}
