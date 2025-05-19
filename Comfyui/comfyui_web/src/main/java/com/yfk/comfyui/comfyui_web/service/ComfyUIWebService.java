package com.yfk.comfyui.comfyui_web.service;

import com.yfk.comfyui.comfyui_dao.dao.ImageDao;
import com.yfk.comfyui.comfyui_service.interfaces.IComfyUIService;
import com.yfk.comfyui.comfyui_service.interfaces.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 14:08
 */
@Service
public class ComfyUIWebService {
    @Autowired
    IFileService fileService;
    @Autowired
    IComfyUIService comfyUIService;
    @Autowired
    ImageDao imageDao;

//    public ResultData<String> uploadImage(LocalFileDTO localFile){
//        ResultData<String> resultData = ResultData.success();
//        FileStoreDTO storeDTO = FileStoreDTO.builder()
//                .localFileDTO(localFile)
//                .build();
//        storeDTO.getNewFileName();
////        // 上传云空间
//        if (!fileService.upload(storeDTO)){
//             resultData = ResultData.fail(ResponseStatusEnum.FAIL.code, "upload image to cloud er");
//            return resultData;
//        }
//
//        //上传comfyui
//        if(!comfyUIService.uploadImage(storeDTO)){
//            resultData = ResultData.fail(ResponseStatusEnum.FAIL.code, "upload image to comfyui er");
//            return resultData;
//        }
//
//        ImageDTO insertImage = ImageDTO.builder().build();
//        insertImage.setImageTypeEnum(ImageSourceEnum.USER_UPLOAD);
//        insertImage.setImageName(storeDTO.getLocalFileDTO().getFileName());
//        insertImage.setImageNewName(storeDTO.getNewFileName());
//        insertImage.setImageOnlineUrl(storeDTO.getRemoteFileDTO().getOnlineUrl());
//        imageDao.insert(insertImage);
//        return resultData;
//
//    }

}
