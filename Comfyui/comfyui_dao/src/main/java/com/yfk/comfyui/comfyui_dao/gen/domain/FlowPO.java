package com.yfk.comfyui.comfyui_dao.gen.domain;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlowPO {
    private Long id;

    private String flowName;

    private String flowDesc;

    private String flowInputs;

    private Date createTime;

    private Date updateTime;

    private String flowPromptDev;
}