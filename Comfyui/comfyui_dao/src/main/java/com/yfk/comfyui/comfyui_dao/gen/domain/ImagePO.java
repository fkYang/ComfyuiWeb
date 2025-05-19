package com.yfk.comfyui.comfyui_dao.gen.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImagePO {
    private Long id;

    private int imageType;

    private String imageName;

    private String imageNewName;

    private String imageOnlineUrl;

    private Long imageFlowId;
}