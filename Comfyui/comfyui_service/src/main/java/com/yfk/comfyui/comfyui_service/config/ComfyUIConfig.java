package com.yfk.comfyui.comfyui_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 13:20
 */
@Component
@ConfigurationProperties(prefix = "comfyui.config")
@Data
public class ComfyUIConfig {
    public String host;
}
