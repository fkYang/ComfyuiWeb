package com.yfk.comfyui.comfyui_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.MediaType;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 13:55
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalFileDTO {
    /**
     * 文件的二进制数据
     */
    private byte[] rawData;
    private String fileName;
    private MediaType contentType;
}
