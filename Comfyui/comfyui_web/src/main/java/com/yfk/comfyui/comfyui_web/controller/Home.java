package com.yfk.comfyui.comfyui_web.controller;

import com.yfk.comfyui.comfyui_common.enums.ImageSourceTypeEnum;
import com.yfk.comfyui.comfyui_dao.dao.ImageDao;
import com.yfk.comfyui.comfyui_dao.dto.ImageDTO;
import com.yfk.comfyui.comfyui_service.interfaces.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/13 16:46
 */
@RestController("/")
public class Home {

    // 图像查看
    @Autowired
    IFileService fileService;

//    @PostMapping("/upload")
//    public String uploadImage(@RequestPart("file") MultipartFile file) throws IOException {
//
//        FileStoreDTO uploaded = FileStoreDTO.builder()
//                .fileName(file.getOriginalFilename())
//                .rawData(file.getBytes())
//                .build();
//        fileService.upload(uploaded);
//        return uploaded.getOnlineUrl();
//    }
        @GetMapping("/")
    public String image() throws IOException {
            return "he";
        }

    @Autowired
    private ImageDao imageDao;
    @GetMapping("/image")
    public String images() throws IOException {
        ImageDTO insertImage = ImageDTO.builder().build();
        insertImage.setImageTypeEnum(ImageSourceTypeEnum.USER_UPLOAD);
        insertImage.setImageName("demo.png");
        insertImage.setImageNewName("111_demo.png");
        insertImage.setImageOnlineUrl("https://raw.githubusercontent.com/fkYang/comfyui_image/main/demo.jpg");

        imageDao.insert(insertImage);
        System.out.println(insertImage.getId());
        return "he";
    }

//    @GetMapping("/image")
//    public Map<String, String> image() throws IOException {
//        String url = "https://raw.githubusercontent.com/fkYang/comfyui_image/main/img/174721391595_%E6%88%AA%E5%B1%8F2025-05-14%2011.38.00.png";
//        FileStoreDTO dto = new FileStoreDTO();
//        dto.setOnlineUrl(url);
//        Map<String, String> result = new HashMap<>();
//        result.put("url", dto.getOnlineUrl());
//        System.out.println(dto.getOnlineUrl());
//        return result;
//
//    }
//    @RequestMapping("/")
//    public ResponseEntity<byte[]> requestMethodName() throws IOException {
//        String url = "https://raw.githubusercontent.com/fkYang/comfyui_image/main/img/174721391595_%E6%88%AA%E5%B1%8F2025-05-14%2011.38.00.png";
//        FileStoreDTO dto = new FileStoreDTO();
//        dto.setOnlineUrl(url);
//
//        fileService.download(dto);
//        System.out.println(dto.getContentType());
//        System.out.println("xx" + dto.getContentType().toString());
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.valueOf(dto.getContentType().toString()))
//                //.contentType(MediaType.valueOf("image/*"))
//                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=3600")
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + "filename" + "\"")
//                .body(dto.getRawData());
//
//    }
}
