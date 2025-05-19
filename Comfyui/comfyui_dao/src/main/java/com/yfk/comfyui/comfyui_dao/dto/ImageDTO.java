package com.yfk.comfyui.comfyui_dao.dto;

import com.yfk.comfyui.comfyui_common.enums.ImageSourceTypeEnum;
import lombok.Builder;
import lombok.Data;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 16:03
 */
@Data
@Builder
public class ImageDTO {
    private Long id;

    private ImageSourceTypeEnum imageTypeEnum;

    private String imageName;

    private String imageNewName;

    private String imageOnlineUrl;

    private Long imageFlowId;
}
