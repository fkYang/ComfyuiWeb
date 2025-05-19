package com.yfk.comfyui.comfyui_service.dto;

import com.yfk.comfyui.comfyui_common.enums.RecordStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/17 17:53
 */
@Data
@Builder
public class RecordResultDTO {
    public RecordStatusEnum statusEnum;
    private List<String> onlineUrls;
}
