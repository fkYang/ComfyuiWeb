package com.yfk.comfyui.comfyui_web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/17 11:28
 */
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // 类级别注解
public class ImageResponse {
    private String imageId;
    private String onlineUrl;
}
