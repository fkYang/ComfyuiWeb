package com.yfk.comfyui.comfyui_service.interfaces;

import com.yfk.comfyui.comfyui_dao.dto.FlowDTO;
import com.yfk.comfyui.comfyui_dao.dto.ImageDTO;
import com.yfk.comfyui.comfyui_service.dto.LocalFileDTO;
import com.yfk.comfyui.comfyui_service.dto.RecordResultDTO;

import java.util.List;
import java.util.Map;

/**
 * desc：承担调用comfyui服务能力
 * author：fkYang
 * date： 2025/5/16 14:51
 */
public interface IComfyUIManagerService {
    // 上传图片到 comfyui ，github  + 库表留存
    ImageDTO uploadImage(LocalFileDTO localFile);

    List<FlowDTO> searchFLow(String name);

    // run，构建请求题，发起对comfyui对调用请求
    // 返回的任务task prompt id 加入全局 concurrentmap中，后续单独发起线程循环看任务结果

    /**
     *
     * @param flowId
     * @param inputMap
     * @return long , 运行记录的id
     */
    Long runComfyUI(Long flowId, Map<String, String> inputMap);

    // 基于 完成任务的 promptid，查看生成的图片，下载图片，上传github 数据库留存
    void saveResult(String promptId);

    RecordResultDTO getResult(Long recordId);
}
