package com.yfk.comfyui.comfyui_common.utils;

import com.alibaba.fastjson2.JSONObject;
import com.yfk.comfyui.comfyui_common.domain.HttpResponseData;
import okhttp3.*;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/13 16:47
 */
//@Log4j2
public class OKHttpUtils {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType MEDIA_TYPE_IMAGE = MediaType.parse("image/*");  // 根据实际文件类型调整

    //    private WebUtils sinInstance;
    private static OkHttpClient mOkHttpClient;

    static {
        /**
         * 构建OkHttpClient
         */
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    private OKHttpUtils() {

    }

    public static HttpResponseData get(String url, Map<String, String> param, Map<String, String> headers) throws IOException {
        StringBuilder baseUrl = new StringBuilder(url);
        if (!CollectionUtils.isEmpty((param))) {
            baseUrl.append("?");
            for (Map.Entry<String, String> entry : param.entrySet()) {
                baseUrl.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }

        Request.Builder builder = new Request.Builder().url(baseUrl.toString());
        addHeader(builder, headers);
        return execute(builder.build());
    }

//    public static HttpResponseData put(String url, Map<String,String> headers, Map<String,String> formData) throws IOException {
//        Request.Builder builder = new Request.Builder()
//                .url(url)
//                .put(buildBody(formData));
//       addHeader(builder, headers);
//        return execute(builder.build());
//    }

    public static HttpResponseData put(String url, Map<String, String> headers, RequestBody body) throws IOException {
        Request.Builder builder = new Request.Builder()
                .url(url)
                .put(body);
        addHeader(builder, headers);
        return execute(builder.build());
    }

    public static RequestBody buildJsonBody(Map<String, String> jsonData) {
        return RequestBody.create(JSONObject.toJSONString(jsonData), JSON);
    }


    private static FormBody.Builder buildFormBody(Map<String, String> formData) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : formData.entrySet()) {
            formBuilder.add(entry.getKey(), entry.getValue());
        }
        return formBuilder;
    }

    private static void addHeader(Request.Builder builder, Map<String, String> headers) {
        if (CollectionUtils.isEmpty(headers)) {
            return;
        }
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
    }

    public static HttpResponseData post(String url, Map<String, String> headers, RequestBody body) throws IOException {
        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(body);
        addHeader(builder, headers);
        return execute(builder.build());
    }

//    public static HttpResponseData postJson(String url, String jsonData)throws IOException {
//        RequestBody body = RequestBody.create(jsonData, JSON);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .addHeader("Content-Type", "application/json")
//                .build();
//        return execute(request);
//    }

    public static HttpResponseData postImage(String url, String imageName, byte[] imageData, Map<String, String> formParams) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .post(buildMultipartBody(imageName, imageData, formParams).build())
                .build();
        return execute(request);
    }

    public static MultipartBody.Builder buildMultipartBody(String imageName, byte[] imageData, Map<String, String> formParams) {
        RequestBody imageBody = RequestBody.create(imageData, MEDIA_TYPE_IMAGE);
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", imageName, imageBody); // "image" 是服务端接收参数名
        // 添加其他表单参数
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            multipartBuilder.addFormDataPart(entry.getKey(), entry.getValue());
        }
        return multipartBuilder;
    }

    public static HttpResponseData execute(Request request) throws IOException {
        try (Response response = mOkHttpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new IOException("请求失败: " + response);
            }
            byte[] bodyBytes = response.body() != null ?
                    response.body().bytes() : new byte[0];
            return HttpResponseData.builder()
                    .code(response.code())
                    .headers(response.headers())
                    .body(response.body())
                    .bodyData(bodyBytes)
                    .build();
        }
    }

}
