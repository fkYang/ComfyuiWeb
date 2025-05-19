package com.yfk.comfyui.comfyui_service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.yfk.comfyui.comfyui_common.domain.HttpResponseData;
import com.yfk.comfyui.comfyui_common.utils.OKHttpUtils;
import com.yfk.comfyui.comfyui_service.config.GithubCondition;
import com.yfk.comfyui.comfyui_service.config.GithubConfig;
import com.yfk.comfyui.comfyui_service.dto.FileStoreDTO;
import com.yfk.comfyui.comfyui_service.dto.LocalFileDTO;
import com.yfk.comfyui.comfyui_service.dto.RemoteFileDTO;
import com.yfk.comfyui.comfyui_service.interfaces.IFileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * desc：基于github平台存储图片数据
 * author：fkYang
 * date： 2025/5/14 14:05
 */
@Log4j2
@Service(value = GithubFileService.NAME)
@Conditional(GithubCondition.class)
public class GithubFileService implements IFileService {
    public static final String NAME = "GithubFileService";

    @Autowired
    private GithubConfig githubConfig;
    private static String URL = "https://api.github.com/repos/OWNER/REPO/contents/PATH";

    @Override
    public boolean upload(FileStoreDTO dto) {
        if(Objects.isNull(dto) || Objects.isNull(dto.getLocalFileDTO())){
            return false;
        }

        String targetUrl = URL.replace("OWNER", githubConfig.owner)
                .replace("REPO", githubConfig.repo)
                .replace("PATH", githubConfig.path);

        targetUrl = targetUrl + dto.getNewFileName();

        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("message", "image upload");
        jsonData.put("content", Base64.getEncoder().encodeToString(dto.getLocalFileDTO().getRawData()));
        //formData.put("branch", GithubConfig.branch);

        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/vnd.github+json");
        header.put("Authorization", "Bearer " + githubConfig.auth);
        header.put("Content-Type", "application/json");

        try {
            HttpResponseData responseData = OKHttpUtils.put(targetUrl, header, OKHttpUtils.buildJsonBody(jsonData));
            String dataStr = new String(responseData.getBodyData());
            JSONObject object = JSONObject.parseObject(dataStr);
            dto.setRemoteFileDTO(RemoteFileDTO.builder()
                    .onlineUrl(object.getJSONObject("content").get("download_url").toString())
                    .build());
        } catch (IOException e) {
            // throw new RuntimeException(e);
            log.info("post file to github err, er:", e);
            return false;
        }
        return true;
    }

    @Override
    public boolean download(FileStoreDTO dto) {
        if (Objects.isNull(dto) || Objects.isNull(dto.getRemoteFileDTO())) {
            return false;
        }
        // 基于在线 url 直接下载
        HttpResponseData responseData;
        try {
            responseData = OKHttpUtils.get(dto.getRemoteFileDTO().getOnlineUrl(), null, null);
            String[] split = dto.getRemoteFileDTO().getOnlineUrl().split("/");
            dto.setLocalFileDTO(LocalFileDTO.builder()
                    .rawData(responseData.getBodyData())
                    .fileName(split[split.length - 1])
                    .contentType(responseData.getBody().contentType())
                    .build());
        } catch (IOException e) {
            log.error("%s download err: %s", dto.getRemoteFileDTO().getOnlineUrl(), e);
            return false;
        }
        return true;
    }

}
