package com.yfk.comfyui.comfyui_web;

import com.yfk.comfyui.comfyui_dao.dto.FlowDTO;
import com.yfk.comfyui.comfyui_service.interfaces.IComfyUIManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 15:36
 */
@SpringBootTest
@ActiveProfiles("dev")
public class ComfyUIManagerTest {
    @Autowired
    IComfyUIManagerService managerService;
    @Test
    public void saveResult(){
        //String promptID = "baaeec38-ba0f-4858-b37a-43721b29318a";
        // sin baaeec38-ba0f-4858-b37a-43721b29318a
        // three  c837bf99-327a-40a9-ac0d-bd033ddaffb3
        String promptID = "c837bf99-327a-40a9-ac0d-bd033ddaffb3";
        promptID = "13200c80-a47e-4275-8c65-c12485681335";
        managerService.saveResult(promptID);
    }

    @Test
    public void flowSearch(){
        List<FlowDTO> flowDTOS = managerService.searchFLow(null);
        System.out.println(flowDTOS);
    }

    @Test
    public void run(){
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("comfyui_flow_inputs_picture", "10");
        inputMap.put("comfyui_flow_inputs_face", "9");
        managerService.runComfyUI(1L, inputMap);
    }

    @Test
    public void queeu(){
        //managerService.equals();
    }
}
