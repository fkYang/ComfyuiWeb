package com.yfk.comfyui.comfyui_service.config;

import lombok.Data;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/14 14:17
 */
@Component
@ConfigurationProperties(prefix = "github.config")
@Data
public class GithubConfig {
    public  String owner;
    public  String repo;
    public  String branch;
    public  String path;
    public  String auth;
}
