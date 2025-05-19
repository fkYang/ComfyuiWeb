package com.yfk.comfyui.comfyui_service.dto.comfyui;

import com.alibaba.fastjson2.PropertyNamingStrategy;
import com.alibaba.fastjson2.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 12:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JSONType(naming = PropertyNamingStrategy.SnakeCase)
public class RunComfyUIResponseDTO {
    private String promptId;
    private int number;
    private String nodeErrors;
}
