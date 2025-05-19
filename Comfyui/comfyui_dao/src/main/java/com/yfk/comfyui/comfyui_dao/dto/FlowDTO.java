package com.yfk.comfyui.comfyui_dao.dto;

import com.yfk.comfyui.comfyui_dao.dto.field.FlowInputDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 16:14
 */
@Data
@Builder
public class FlowDTO {
    private Long id;

    private String flowName;

    private String flowDesc;

    private String flowPromptDev;

    private List<FlowInputDTO> flowInputs;
}
