package com.yfk.comfyui.comfyui_service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.yfk.comfyui.comfyui_common.domain.HttpResponseData;
import com.yfk.comfyui.comfyui_common.utils.OKHttpUtils;
import com.yfk.comfyui.comfyui_service.config.ComfyUIConfig;
import com.yfk.comfyui.comfyui_service.dto.*;
import com.yfk.comfyui.comfyui_service.dto.comfyui.ComfyUITaskResultDTO;
import com.yfk.comfyui.comfyui_service.dto.comfyui.QueueResponseDTO;
import com.yfk.comfyui.comfyui_service.dto.comfyui.RunComfyUIRequestDTO;
import com.yfk.comfyui.comfyui_service.dto.comfyui.RunComfyUIResponseDTO;
import com.yfk.comfyui.comfyui_service.interfaces.IComfyUIService;
import lombok.extern.log4j.Log4j2;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 13:19
 */
@Service
@Log4j2
public class ComfyUIServiceImpl implements IComfyUIService {

    @Autowired
    private ComfyUIConfig comfyUIConfig;
    private static final String UPLOAD_IMAGE_URI = "/api/upload/image";
    private static final String DOWNLOAD_IMAGE_URI = "/view";

    private static final String RUN_COMFYUI_URI = "/api/prompt";
    private static final String TASK_RESULT_URI = "/api/history/";
    private static final String QUEUE_URI = "/api/queue";



    @Override
    public boolean uploadImage(FileStoreDTO dto) {
        if (Objects.isNull(dto) || Objects.isNull(dto.getLocalFileDTO())) {
            return false;
        }
        String url = comfyUIConfig.host + UPLOAD_IMAGE_URI;
        LocalFileDTO localFileDTO = dto.getLocalFileDTO();
        try {
            // todo 后续基于用户？分类不同的文件夹
            HttpResponseData responseData = OKHttpUtils.postImage(url, dto.getNewFileName(), localFileDTO.getRawData(), new HashMap<>());
        } catch (IOException e) {
            log.error("ComfyUIServiceImpl uploadImage file:%s  err:%s", localFileDTO.getFileName(), e);
            return false;
        }
        return true;

    }

    @Override
    public FileStoreDTO downloadImage(Map<String, String> uriParams) {
        String url = comfyUIConfig.host + DOWNLOAD_IMAGE_URI;
        FileStoreDTO dto = new FileStoreDTO();
        try {
            HttpResponseData responseData = OKHttpUtils.get(url, uriParams, null);
            dto.setLocalFileDTO(LocalFileDTO.builder()
                    .rawData(responseData.getBodyData())
                    .fileName(uriParams.get("filename"))
                    .contentType(responseData.getBody().contentType())
                    .build());
        } catch (IOException e) {
            log.error("ComfyUIServiceImpl downloadImage  file;{}, err:{},", uriParams, e);
        }
        return dto;
    }

    @Override
    public RunComfyUIResponseDTO runComfyUI(RunComfyUIRequestDTO dto) {
        RunComfyUIResponseDTO responseDTO = new RunComfyUIResponseDTO();
        if (Objects.isNull(dto)) {
            return responseDTO;
        }

        JSONObject data = new JSONObject();
        data.put("client_id", dto.getClintID());
        data.put("prompt", dto.getPrompt());
        RequestBody body = RequestBody.create(JSONObject.toJSONString(data), MediaType.parse("application/json; charset=utf-8"));

        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("client_id", dto.getClintID());
        jsonData.put("prompt", JSONObject.toJSONString(dto.getPrompt()));

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        try {
            HttpResponseData responseData = OKHttpUtils.post(comfyUIConfig.host + RUN_COMFYUI_URI,
                    header, body);
            String dataStr = new String(responseData.getBodyData());
            responseDTO = JSONObject.parseObject(dataStr, RunComfyUIResponseDTO.class);
        } catch (IOException e) {
            // throw new RuntimeException(e);
            log.error("runComfyUI err, er:", e);
            return responseDTO;
        }

        return responseDTO;
    }

    @Override
    public ComfyUITaskResultDTO getTaskResult(String promptID) {
        ComfyUITaskResultDTO result = null;
        String url = comfyUIConfig.host + TASK_RESULT_URI + promptID;
        try {
            HttpResponseData responseData = OKHttpUtils.get(url, null, null);
            String dataStr = new String(responseData.getBodyData());
            Map<String, ComfyUITaskResultDTO> resultMap = JSONObject.parseObject(dataStr, new TypeReference<Map<String, ComfyUITaskResultDTO>>() {
            });
            result = resultMap.get(promptID);
        } catch (IOException e) {
            // throw new RuntimeException(e);
            log.error("runComfyUI err, er:", e);
        }
        return result;
    }

    @Override
    public QueueResponseDTO getQueue() {
        QueueResponseDTO result = QueueResponseDTO.builder().build();
        String url = comfyUIConfig.host + QUEUE_URI;
        try {
            HttpResponseData responseData = OKHttpUtils.get(url, null, null);
            String dataStr = new String(responseData.getBodyData());
            result = JSONObject.parseObject(dataStr, QueueResponseDTO.class);
        } catch (IOException e) {
            log.error("getQueue err:{}", e);
        }
        return result;
    }
}
