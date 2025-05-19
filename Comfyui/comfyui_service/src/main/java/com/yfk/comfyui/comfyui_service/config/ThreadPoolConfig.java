package com.yfk.comfyui.comfyui_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 20:14
 */
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "comfyuiTaskThreadPool")
    public Executor comfyuiTaskThreadPool() {
        return new ThreadPoolExecutor(
                5, 10, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
//                new CustomThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }


}
