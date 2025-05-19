package com.yfk.comfyui.comfyui_web;

import com.alibaba.fastjson2.JSONObject;
import com.yfk.comfyui.comfyui_common.enums.ImageSourceTypeEnum;
import com.yfk.comfyui.comfyui_dao.dao.ImageDao;
import com.yfk.comfyui.comfyui_dao.dto.ImageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 17:31
 */
@SpringBootTest
@ActiveProfiles("dev")
public class MyTest {
    @Autowired
    private ImageDao userDao;


    @Test
    public void test(){
        List<Long> data = new ArrayList<>();
        data.add(1L);
        String str = JSONObject.toJSONString(data);
        List list = JSONObject.parseObject(str, List.class);
        System.out.println(list);

    }

    @Test
    public void insert() {
        ImageDTO insertImage = ImageDTO.builder().build();
        insertImage.setImageTypeEnum(ImageSourceTypeEnum.USER_UPLOAD);
        insertImage.setImageName("demo.png");
        insertImage.setImageNewName("111_demo.png");
        insertImage.setImageOnlineUrl("https://raw.githubusercontent.com/fkYang/comfyui_image/main/demo.jpg");

        userDao.insert(insertImage);
        System.out.println(insertImage.getId());
    }

    @Test
    public void search(){
        ImageDTO user = userDao.searchById( 1L);
        System.out.println(user.getId());
    }
}
