package com.yfk.comfyui.comfyui_common.utils;

import com.yfk.comfyui.comfyui_common.enums.CodeEnum;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 16:14
 */
public class EnumConvertUtils {

    public static <T, E extends Enum<E> & CodeEnum<T>> E toEnum(T code, Class<E> enumType) {
        return CodeEnum.fromValue(enumType, code);
    }
}
