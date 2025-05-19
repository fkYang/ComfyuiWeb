package com.yfk.comfyui.comfyui_web;

import com.yfk.comfyui.comfyui_dao.dao.FlowRunRecordDao;
import com.yfk.comfyui.comfyui_dao.dto.FlowRunRecordsDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 17:48
 */
@SpringBootTest
@ActiveProfiles("dev")
public class Recordtest {

    @Autowired
    FlowRunRecordDao dao;
    @Test
    public  void save(){
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("comfyui_flow_inputs_picture", "10");
        inputMap.put("comfyui_flow_inputs_face", "9");

        List<Long> images = new ArrayList<>();
        images.add(10L);

        FlowRunRecordsDTO dto = FlowRunRecordsDTO.builder()
                .inputs(inputMap)
                .outputImage(images)
                .flowId(1L)
                .build();
        dao.insert(dto);
    }

    @Test
    public  void query(){
        FlowRunRecordsDTO dto = dao.searchById(1L);
        System.out.println(dto);
    }



}
