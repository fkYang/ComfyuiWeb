package com.yfk.comfyui.comfyui_service.interfaces;

import com.yfk.comfyui.comfyui_service.dto.comfyui.ComfyUITaskResultDTO;
import com.yfk.comfyui.comfyui_service.dto.FileStoreDTO;
import com.yfk.comfyui.comfyui_service.dto.comfyui.QueueResponseDTO;
import com.yfk.comfyui.comfyui_service.dto.comfyui.RunComfyUIRequestDTO;
import com.yfk.comfyui.comfyui_service.dto.comfyui.RunComfyUIResponseDTO;

import java.util.Map;

/**
 * desc： 直接和comfyui交互的服务
 * author：fkYang
 * date： 2025/5/15 13:15
 */
public interface IComfyUIService {
    // 上传二进制图片到comfyui服务器
    boolean uploadImage(FileStoreDTO dto);

    FileStoreDTO downloadImage(Map<String, String> uriParams);

    RunComfyUIResponseDTO runComfyUI(RunComfyUIRequestDTO dto);

    ComfyUITaskResultDTO getTaskResult(String promptID);
    // 下载图片到github

    // 提交参数运行工作流

    // get prompt 获取正在执行的任务信息
    QueueResponseDTO getQueue();

}
