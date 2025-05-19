package com.yfk.comfyui.comfyui_service.dto.comfyui;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 12:42
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RunComfyUIRequestDTO {
    private String clintID;
    private Map<String, Object> prompt;

    //private Map<String, PromptNode> prompt;

//    public static class PromptNode {
//        private Map<String, Object> inputs;
//        private String classType;
//        private Meta meta;
//    }
//    public static class Meta {
//        private String title;
//        public Meta(String title) {
//            this.title = title;
//        }
//    }
}
