package com.yfk.comfyui.comfyui_dao.dto;

import com.yfk.comfyui.comfyui_common.enums.RecordStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 16:15
 */
@Data
@Builder
public class FlowRunRecordsDTO {
    private Long id;

    private Long flowId;

    // map < 请求参数， 实际值>
    private Map<String, String> inputs;

    // list<>Long
    private List<Long> outputImage;

    private RecordStatusEnum status;

    private String promptId;
}
