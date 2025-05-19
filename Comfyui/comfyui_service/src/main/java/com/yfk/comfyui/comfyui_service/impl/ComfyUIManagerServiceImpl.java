package com.yfk.comfyui.comfyui_service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.yfk.comfyui.comfyui_common.enums.FlowInputTypeEnum;
import com.yfk.comfyui.comfyui_common.enums.ImageSourceTypeEnum;
import com.yfk.comfyui.comfyui_common.enums.RecordStatusEnum;
import com.yfk.comfyui.comfyui_dao.dao.FlowDao;
import com.yfk.comfyui.comfyui_dao.dao.FlowRunRecordDao;
import com.yfk.comfyui.comfyui_dao.dao.ImageDao;
import com.yfk.comfyui.comfyui_dao.dto.FlowDTO;
import com.yfk.comfyui.comfyui_dao.dto.FlowRunRecordsDTO;
import com.yfk.comfyui.comfyui_dao.dto.ImageDTO;
import com.yfk.comfyui.comfyui_dao.dto.field.FlowInputDTO;
import com.yfk.comfyui.comfyui_service.dto.FileStoreDTO;
import com.yfk.comfyui.comfyui_service.dto.LocalFileDTO;
import com.yfk.comfyui.comfyui_service.dto.RecordResultDTO;
import com.yfk.comfyui.comfyui_service.dto.comfyui.ComfyUITaskResultDTO;
import com.yfk.comfyui.comfyui_service.dto.comfyui.QueueResponseDTO;
import com.yfk.comfyui.comfyui_service.dto.comfyui.RunComfyUIRequestDTO;
import com.yfk.comfyui.comfyui_service.dto.comfyui.RunComfyUIResponseDTO;
import com.yfk.comfyui.comfyui_service.interfaces.IComfyUIManagerService;
import com.yfk.comfyui.comfyui_service.interfaces.IComfyUIService;
import com.yfk.comfyui.comfyui_service.interfaces.IFileService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 15:00
 */
@Service
public class ComfyUIManagerServiceImpl implements IComfyUIManagerService {
    @Autowired
    IComfyUIService comfyUIService;
    @Autowired
    IFileService fileService;

    @Autowired
    ImageDao imageDao;
    @Autowired
    FlowDao flowDao;
    @Autowired
    FlowRunRecordDao recordDao;

    @Autowired
    @Qualifier("comfyuiTaskThreadPool")
    Executor task;

    private static final ConcurrentHashMap<String, Long> taskMap = new ConcurrentHashMap<>();
    // 加入

    @PostConstruct
    private void runTask() {
        System.out.println("init add task");
        task.execute(new Runnable() {
            @Override
            public void run() {
                cycleCalResult();
            }
        });
    }

    private void cycleCalResult() {
        while (true) {
            while (taskMap.isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String[] waitingPrompts = taskMap.keySet().toArray(new String[0]);

            // 获取还没有执行成功的任务
            QueueResponseDTO queue = comfyUIService.getQueue();
            Set<String> queuePrompts = queue.getPrompts();
            //List<String>
            for (String prompt : waitingPrompts) {
                if (queuePrompts.contains(prompt)) {
                    continue;
                }
//                // todo log
//                System.out.println("get result :  %s" + prompt);
                // 已经完成的任务
                saveResult(prompt);

            }
        }
    }

    @Override
    public ImageDTO uploadImage(LocalFileDTO localFile) {
        FileStoreDTO storeDTO = FileStoreDTO.builder()
                .localFileDTO(localFile)
                .build();
        storeDTO.getNewFileName();
        // 上传云空间
        if (!fileService.upload(storeDTO)) {
            return null;
        }
        //上传comfyui
        if (!comfyUIService.uploadImage(storeDTO)) {
            return null;
        }

        ImageDTO insertImage = ImageDTO.builder().build();
        insertImage.setImageTypeEnum(ImageSourceTypeEnum.USER_UPLOAD);
        insertImage.setImageName(storeDTO.getLocalFileDTO().getFileName());
        insertImage.setImageNewName(storeDTO.getNewFileName());
        insertImage.setImageOnlineUrl(storeDTO.getRemoteFileDTO().getOnlineUrl());
        // imageDao.insert(insertImage);
        saveUploadImage(insertImage);
        return insertImage;
    }

    @Override
    public List<FlowDTO> searchFLow(String name) {
        FlowDTO flowDTO = FlowDTO.builder()
                .flowName(name)
                .build();
        return flowDao.search(flowDTO);
    }

    @Transactional
    public Long saveUploadImage(ImageDTO insertImage) {
        //todo 注解是否生效
        imageDao.insert(insertImage);
        return insertImage.getId();
    }


    @Override
    public Long runComfyUI(Long flowId, Map<String, String> inputMap) {
        FlowDTO flowDTO = flowDao.searchById(flowId);
        String promptStr = flowDTO.getFlowPromptDev();
        // 替换参数
        for (FlowInputDTO inputDTO : flowDTO.getFlowInputs()) {
            String inputParam = inputMap.get(inputDTO.getReplacedStr());
            String targetParam = inputParam;
            if (FlowInputTypeEnum.IMAGE.getCode().equals(inputDTO.getType())) {
                Long id = Long.valueOf(inputParam);
                ImageDTO imageDTO = imageDao.searchById(id);
                targetParam = imageDTO.getImageNewName();
            }
            promptStr = promptStr.replace(inputDTO.getReplacedStr(), targetParam);
        }

        RunComfyUIRequestDTO requestDTO = RunComfyUIRequestDTO.builder()
                .clintID(System.currentTimeMillis() + "")
                .prompt(JSONObject.parseObject(promptStr, Map.class))
                .build();
        RunComfyUIResponseDTO uiResponseDTO = comfyUIService.runComfyUI(requestDTO);
        // todo add pormpt_id to task
        // prompt ++ recordId
        String promptId = uiResponseDTO.getPromptId();

        //todo record save
        FlowRunRecordsDTO recordsDTO = FlowRunRecordsDTO.builder()
                .flowId(1L)
                .inputs(inputMap)
                .promptId(promptId)
                .status(RecordStatusEnum.WAITING)
                .build();
        recordDao.insert(recordsDTO);
        // 添加到 map里，后台线程监听执行
        taskMap.put(promptId, recordsDTO.getId());
        return recordsDTO.getId();
    }

    @Override
    public void saveResult(String promptId) {


        // 获取返回值
        ComfyUITaskResultDTO taskResult = comfyUIService.getTaskResult(promptId);
        List<Map<String, String>> imageUriParams = new ArrayList<>();
        taskResult.getOutputs().forEach((k, v) -> {
            v.getImages().forEach(ele -> {
                Map<String, String> params = new HashMap<>();
                params.put("filename", ele.getFilename());
                params.put("type", ele.getType());
                imageUriParams.add(params);
            });
        });

        //下载图片
        List<FileStoreDTO> files = new ArrayList<>();
        for (int i = 0; i < imageUriParams.size(); i++) {
            Map<String, String> params = imageUriParams.get(i);
            FileStoreDTO storeDTO = comfyUIService.downloadImage(params);
            files.add(storeDTO);
        }

        //上传github
        files.forEach(ele -> {
            fileService.upload(ele);
        });

        // todo 查询数据库，获取运行记录
        Long recordId = taskMap.get(promptId);
        FlowRunRecordsDTO recordDto = recordDao.searchById(recordId);
        //String promptId = dto.getPromptId();

        // 数据库留存
        List<ImageDTO> imageDTOS = new ArrayList<>();
        for (FileStoreDTO storeDTO : files) {
            ImageDTO imageDTO = ImageDTO.builder()
                    .imageFlowId(recordDto.getFlowId()) //todo  flowId
                    .imageName(storeDTO.getLocalFileDTO().getFileName())
                    .imageNewName(storeDTO.getNewFileName())
                    .imageOnlineUrl(storeDTO.getRemoteFileDTO().getOnlineUrl())
                    .imageTypeEnum(ImageSourceTypeEnum.COMFYUI_GEN)
                    .build();
            imageDao.insert(imageDTO);
            imageDTOS.add(imageDTO);
        }
        //todo 运行记录增加 生成结果 + 状态流转
        List<Long> imageList = imageDTOS.stream().map(ele -> ele.getId()).toList();
        recordDto.setOutputImage(imageList);
        recordDto.setStatus(RecordStatusEnum.DONE);
        recordDao.save(recordDto);

        taskMap.remove(promptId);
    }

    @Override
    public RecordResultDTO getResult(Long recordId) {
        RecordResultDTO result = RecordResultDTO.builder().build();
        result.setStatusEnum(RecordStatusEnum.WAITING);

        FlowRunRecordsDTO dto = recordDao.searchById(recordId);
        if (RecordStatusEnum.DONE.equals(dto.getStatus())) {
            List<ImageDTO> imageDTOS = imageDao.searchByIds(dto.getOutputImage());
            List<String> urls = imageDTOS.stream().map(ele -> ele.getImageOnlineUrl()).toList();

            result.setOnlineUrls(urls);
            result.setStatusEnum(RecordStatusEnum.DONE);
        }
        return result;
    }

}
