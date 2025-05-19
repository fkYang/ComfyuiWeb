package com.yfk.comfyui.comfyui_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.MediaType;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/14 14:07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileStoreDTO {
    private LocalFileDTO localFileDTO;
    private RemoteFileDTO remoteFileDTO;

//    /**
//     * 文件的二进制数据
//     */
//    private byte[] rawData;
//    private String fileName;
//    private MediaType contentType;


    // 存储的远程
    private String newFileName;
//    private String onlineUrl;

    /**
     * 无并发场景
     * @return
     */
    public String getNewFileName(){
        if(Objects.isNull(localFileDTO)){
            return "";
        }
        if(StringUtils.isBlank(newFileName)){
            newFileName = System.currentTimeMillis()+ "_" + localFileDTO.getFileName();
        }
        return newFileName;
    }
}
