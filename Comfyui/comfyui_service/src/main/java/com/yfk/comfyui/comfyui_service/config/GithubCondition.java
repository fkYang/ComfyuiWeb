package com.yfk.comfyui.comfyui_service.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/14 15:27
 */
public class GithubCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 从配置文件中读取 payment.type 的值
        String paymentType = context.getEnvironment().getProperty("file_upload.type");
        return "github".equalsIgnoreCase(paymentType);
    }
}
