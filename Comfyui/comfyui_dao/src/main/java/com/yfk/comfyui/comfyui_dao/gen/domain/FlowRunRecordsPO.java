package com.yfk.comfyui.comfyui_dao.gen.domain;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlowRunRecordsPO {
    private Long id;

    private Long flowId;

    private String inputs;

    private String outputImage;

    private Date createTime;

    private Date updateTime;

    private int status;

    private String promptId;
}