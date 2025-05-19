package com.yfk.comfyui.comfyui_service.dto.comfyui;

import com.alibaba.fastjson2.PropertyNamingStrategy;
import com.alibaba.fastjson2.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 14:10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JSONType(naming = PropertyNamingStrategy.SnakeCase)
public class ComfyUITaskResultDTO {
    // 最后一个是输出列表 list的数组
//    private List<Object> prompt;

    private Map<String, OutputNode> outputs;
    @Data
    public static class OutputNode{
        private List<Images> images;
    }
    @Data
    public static class Images{
        private String filename;
        private String subfolder;
        private String type;
    }

    private StatusNode status;
    @Data
    public static class StatusNode{
        // success 成功
        private String statusStr;
        private boolean completed;
        private Object messages;
    }
}
