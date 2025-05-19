package com.yfk.comfyui.comfyui_web.controller;

import com.yfk.comfyui.comfyui_dao.dto.FlowDTO;
import com.yfk.comfyui.comfyui_dao.dto.ImageDTO;
import com.yfk.comfyui.comfyui_service.dto.LocalFileDTO;
import com.yfk.comfyui.comfyui_service.dto.RecordResultDTO;
import com.yfk.comfyui.comfyui_service.interfaces.IComfyUIManagerService;
import com.yfk.comfyui.comfyui_web.dto.FlowResponse;
import com.yfk.comfyui.comfyui_web.dto.ImageResponse;
import com.yfk.comfyui.comfyui_web.dto.RunRequest;
import com.yfk.comfyui.comfyui_web.dto.RunResultResponse;
import com.yfk.comfyui.comfyui_web.enums.ResponseStatusEnum;
import com.yfk.comfyui.comfyui_web.mapper.FlowResponseMapper;
import com.yfk.comfyui.comfyui_web.response.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 14:03
 */
@RestController
@RequestMapping("/comfyui")
public class ComfyUIController {

    @Autowired
    IComfyUIManagerService managerService;

    /**
     * 上传图片，存储到云空间, 落库，然后再上传到comfyui
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public ResultData<ImageResponse> uploadImage(@RequestPart("file") MultipartFile file) throws IOException {
        LocalFileDTO localFileDTO =  LocalFileDTO.builder()
                .rawData(file.getBytes())
                .fileName(file.getOriginalFilename())
                .build();
        ResultData<ImageResponse>  result = ResultData.success();
        try {
            ImageDTO image = managerService.uploadImage(localFileDTO);

            if(Objects.isNull(image) ||image.getId().compareTo(0L) <= 0){
                result = ResultData.fail(ResponseStatusEnum.FAIL.code, "uploadImage er");
            }
            result.setData(ImageResponse.builder()
                            .imageId(String.valueOf(image.getId()))
                            .onlineUrl(image.getImageOnlineUrl())
                    .build());
        }catch (Exception e){
            result = ResultData.fail(ResponseStatusEnum.FAIL.code, "uploadImage er");
        }
        return result;
    }

    /**
     * 返回体，record 记录的id
     * @param runRequest
     * @return
     */
    @PostMapping("/run")
    public ResultData<String> runComfyUI( @RequestBody RunRequest runRequest){
//        Map<String, String> inputMap = new HashMap<>();
//        inputMap.put("comfyui_flow_inputs_picture", "10");
//        inputMap.put("comfyui_flow_inputs_face", "9");
        Long l = managerService.runComfyUI(Long.valueOf(runRequest.getFlowId()), runRequest.getInputMap());
        ResultData<String>  result = ResultData.success();
        result.setData(l.toString());
        return result;
    }

    @GetMapping("/flow_search")
    public ResultData<List<FlowResponse>> search(@RequestParam(value = "name", required = false) String name){
        List<FlowDTO> flowDTOS = managerService.searchFLow(name);
        List<FlowResponse> flowResponses = FlowResponseMapper.INSTANCE.flowListConvert(flowDTOS);
        ResultData<List<FlowResponse>>  result = ResultData.success();
        result.setData(flowResponses);
        return result;
    }

    @GetMapping("/run_result")
    public ResultData<RunResultResponse> getResult(@RequestParam("recordId") String recordId){

        RecordResultDTO recordResult = managerService.getResult(Long.valueOf(recordId));

        RunResultResponse resultData = RunResultResponse.builder()
                .status(recordResult.getStatusEnum().getCode())
                .statusStr(recordResult.getStatusEnum().name())
                .urls(recordResult.getOnlineUrls())
                .build();
        ResultData<RunResultResponse>  result = ResultData.success();
        result.setData(resultData);
        return result;

    }
}
