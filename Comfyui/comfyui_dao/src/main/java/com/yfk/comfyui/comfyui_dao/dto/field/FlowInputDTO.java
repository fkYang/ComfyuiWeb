package com.yfk.comfyui.comfyui_dao.dto.field;

import lombok.Builder;
import lombok.Data;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 16:16
 */
@Data
@Builder
public class FlowInputDTO {
    private String desc;
    private Integer type;
    private String replacedStr;
}
