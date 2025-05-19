package com.yfk.comfyui.comfyui_web;

import com.yfk.comfyui.comfyui_service.config.ThreadPoolConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(value = {
		"com.yfk.comfyui.comfyui_service",
		"com.yfk.comfyui.comfyui_web",
		"com.yfk.comfyui.comfyui_dao"
})
@EnableConfigurationProperties
@MapperScan("com.yfk.comfyui.comfyui_dao")
//@Import(ThreadPoolConfig.class)
public class ComfyuiWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComfyuiWebApplication.class, args);
	}

}
