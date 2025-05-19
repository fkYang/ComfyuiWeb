package com.yfk.comfyui.comfyui_web.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 21:45
 */
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // 类级别注解
public class RunRequest {
    private String flowId;
    private Map<String, String> inputMap;
}
