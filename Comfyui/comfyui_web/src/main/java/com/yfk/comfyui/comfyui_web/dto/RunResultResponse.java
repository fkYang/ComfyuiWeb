package com.yfk.comfyui.comfyui_web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/17 18:03
 */
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RunResultResponse {
    /**
     * 0 waiting, 1 done
     */
    private int status;

    private String statusStr;

    private List<String> urls;

}
