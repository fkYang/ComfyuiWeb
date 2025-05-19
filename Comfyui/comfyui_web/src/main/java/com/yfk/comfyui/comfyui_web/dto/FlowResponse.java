package com.yfk.comfyui.comfyui_web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/17 11:53
 */
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // 类级别注解
public class FlowResponse {
    private String id;
    private String flowName;
    private String flowDesc;
    List<InputNode> inputNodes;

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @Data
    static public class InputNode{
        private String desc;
        private Integer type;
        private String replacedStr;
    }

}
