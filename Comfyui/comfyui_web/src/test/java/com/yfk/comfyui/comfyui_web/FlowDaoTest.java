package com.yfk.comfyui.comfyui_web;

import com.yfk.comfyui.comfyui_dao.dao.FlowDao;
import com.yfk.comfyui.comfyui_dao.dto.FlowDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 16:32
 */
@SpringBootTest
@ActiveProfiles("dev")
public class FlowDaoTest {
    @Autowired
    private FlowDao flowDao;

    @Test
    public void search(){
        FlowDTO flowDTO = flowDao.searchById(1L);
        System.out.println(flowDTO);
    }
}
