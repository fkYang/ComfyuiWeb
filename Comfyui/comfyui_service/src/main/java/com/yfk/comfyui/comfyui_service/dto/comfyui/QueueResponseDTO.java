package com.yfk.comfyui.comfyui_service.dto.comfyui;

import com.alibaba.fastjson2.PropertyNamingStrategy;
import com.alibaba.fastjson2.annotation.JSONCreator;
import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.annotation.JSONType;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * desc：/api/queue 返回的最小任务DTO
 * author：fkYang
 * date： 2025/5/16 19:37
 */
@Builder
@Data
@JSONType(naming = PropertyNamingStrategy.SnakeCase)
public class QueueResponseDTO {
    /**
     * list index 0: 任务序号
     *      index 1: promptId , string 类型
     */
   // @JSONField(name = "queue_running")
    List<List<Object>> queueRunning;
   // @JSONField(name = "queue_pending")
    List<List<Object>> queuePending;

    public Set<String> getPrompts(){
        Set<String> prompts = new HashSet<>();
        queueRunning.forEach( ele->{
            prompts.add(ele.get(1).toString());
        });
        queuePending.forEach( ele->{
            prompts.add(ele.get(1).toString());

        });
        return prompts;
    }

//    @JSONField(name = "queue_running")
//    List<List<QueueItem>> queueRunning;
//    @JSONField(name = "queue_pending")
//    List<List<QueueItem>> queuePending;

//    @Data
//    public static class QueueItem{
//        private Integer id;
//        private String promptId;
//        //private Object prompt
//
//        /**
//         * 自定义反序列化逻辑：将 JSON 数组转换为对象, 目前只存储前两个
//         */
//        @JSONCreator
//        public QueueItem(Object[] values) {
//            this.id = ((Number) values[0]).intValue();  // 处理数字类型
//            this.promptId = (String) values[1];             // 直接转为字符串
//
//        }
//    }
}
