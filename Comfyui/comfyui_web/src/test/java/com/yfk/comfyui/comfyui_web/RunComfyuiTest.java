package com.yfk.comfyui.comfyui_web;

import com.alibaba.fastjson2.JSONObject;
import com.yfk.comfyui.comfyui_service.dto.comfyui.ComfyUITaskResultDTO;
import com.yfk.comfyui.comfyui_service.dto.comfyui.QueueResponseDTO;
import com.yfk.comfyui.comfyui_service.dto.comfyui.RunComfyUIRequestDTO;
import com.yfk.comfyui.comfyui_service.dto.comfyui.RunComfyUIResponseDTO;
import com.yfk.comfyui.comfyui_service.interfaces.IComfyUIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 13:09
 */
@SpringBootTest
@ActiveProfiles("dev")
public class RunComfyuiTest {

    @Autowired
    IComfyUIService service;

    @Test
    public void queue(){
        QueueResponseDTO queue = service.getQueue();
        System.out.println(queue.getPrompts());
    }

    @Test
    public void result(){
        String prompt = "c837bf99-327a-40a9-ac0d-bd033ddaffb3";
        ComfyUITaskResultDTO taskResult = service.getTaskResult(prompt);

        List<String> images = new ArrayList<>();
        taskResult.getOutputs().forEach((k,v) -> {
            v.getImages().forEach(ele -> {
                //ele.getFilename();
                String imageURI = "?filename=" + ele.getFilename() + "&type=" + ele.getType();
                System.out.println(imageURI);
                images.add(imageURI);

            });
        });
//        for(Map.Entry<String, ComfyUITaskResultDTO> index : taskResult.entrySet()){
//            System.out.println(index.getKey());
            // images
    }

    @Test
    public void run() {
        RunComfyUIRequestDTO requestDTO = new RunComfyUIRequestDTO();
        String prompt = "{\n" +
                "\t\t\"1\": {\n" +
                "\t\t\t\"inputs\": {\n" +
                "\t\t\t\t\"image\": \"trump1.jpg\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"class_type\": \"LoadImage\",\n" +
                "\t\t\t\"_meta\": {\n" +
                "\t\t\t\t\"title\": \"Load Image\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"3\": {\n" +
                "\t\t\t\"inputs\": {\n" +
                "\t\t\t\t\"enabled\": true,\n" +
                "\t\t\t\t\"swap_model\": \"inswapper_128.onnx\",\n" +
                "\t\t\t\t\"facedetection\": \"retinaface_resnet50\",\n" +
                "\t\t\t\t\"face_restore_model\": \"GFPGANv1.4.pth\",\n" +
                "\t\t\t\t\"face_restore_visibility\": 1,\n" +
                "\t\t\t\t\"codeformer_weight\": 0.5,\n" +
                "\t\t\t\t\"detect_gender_input\": \"no\",\n" +
                "\t\t\t\t\"detect_gender_source\": \"no\",\n" +
                "\t\t\t\t\"input_faces_index\": \"0\",\n" +
                "\t\t\t\t\"source_faces_index\": \"0\",\n" +
                "\t\t\t\t\"console_log_level\": 1,\n" +
                "\t\t\t\t\"input_image\": [\n" +
                "\t\t\t\t\t\"1\",\n" +
                "\t\t\t\t\t0\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"source_image\": [\n" +
                "\t\t\t\t\t\"6\",\n" +
                "\t\t\t\t\t0\n" +
                "\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"class_type\": \"ReActorFaceSwap\",\n" +
                "\t\t\t\"_meta\": {\n" +
                "\t\t\t\t\"title\": \"ReActor \uD83C\uDF0C Fast Face Swap\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"4\": {\n" +
                "\t\t\t\"inputs\": {\n" +
                "\t\t\t\t\"images\": [\n" +
                "\t\t\t\t\t\"3\",\n" +
                "\t\t\t\t\t0\n" +
                "\t\t\t\t]\n" +
                "\t\t\t},\n" +
                "\t\t\t\"class_type\": \"PreviewImage\",\n" +
                "\t\t\t\"_meta\": {\n" +
                "\t\t\t\t\"title\": \"Preview Image\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"6\": {\n" +
                "\t\t\t\"inputs\": {\n" +
                "\t\t\t\t\"image\": \"马斯克1.jpg\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"class_type\": \"LoadImage\",\n" +
                "\t\t\t\"_meta\": {\n" +
                "\t\t\t\t\"title\": \"Load Image\"\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}";
        Map promptNode = JSONObject.parseObject(prompt, Map.class);
        requestDTO.setClintID( "num" + System.currentTimeMillis());
        requestDTO.setPrompt(promptNode);
        RunComfyUIResponseDTO responseDTO = service.runComfyUI(requestDTO);
        System.out.println(responseDTO);
    }
}
