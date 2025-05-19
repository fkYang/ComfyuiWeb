package com.yfk.comfyui.comfyui_web.response;

import com.yfk.comfyui.comfyui_web.enums.ResponseStatusEnum;
import lombok.Data;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 14:18
 */
@Data
public class ResultData<T> {
    private int status;
    private String message;
    private T data;
    public static <T> ResultData<T> success() {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(ResponseStatusEnum.SUCCESS.code);
        resultData.setMessage(ResponseStatusEnum.SUCCESS.message);

        return resultData;
    }

    public static <T> ResultData<T> fail(int code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        return resultData;
    }
}
