package com.yfk.comfyui.comfyui_service.interfaces;

import com.yfk.comfyui.comfyui_service.dto.FileStoreDTO;

/**
 * desc：文件（图像）的上传转换能力
 * author：fkYang
 * date： 2025/5/14 14:05
 */
public interface IFileService {
    /**
     * 上传图片，把 二进制图片上传云，获取url访问链接
     * @param dto
     */
    boolean upload(FileStoreDTO dto);

    /**
     * 下载图片，基于url获取二进制文件
     * @param dto
     */
    boolean download(FileStoreDTO dto);
}
