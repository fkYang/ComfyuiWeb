package com.yfk.comfyui.comfyui_common.domain;

import lombok.Builder;
import lombok.Data;
import okhttp3.Headers;
import okhttp3.ResponseBody;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/13 19:48
 */
@Data
@Builder
public class HttpResponseData {
    private  int code;
    private  Headers headers;
    private  byte[] bodyData;
    private  ResponseBody body;
    private  String bodyStr;
}
