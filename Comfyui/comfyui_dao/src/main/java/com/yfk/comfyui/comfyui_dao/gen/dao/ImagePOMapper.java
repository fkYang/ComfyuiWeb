package com.yfk.comfyui.comfyui_dao.gen.dao;

import com.yfk.comfyui.comfyui_dao.gen.domain.ImagePO;
import com.yfk.comfyui.comfyui_dao.gen.domain.ImagePOExample;
import java.util.List;

public interface ImagePOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ImagePO record);

    int insertSelective(ImagePO record);

    List<ImagePO> selectByExample(ImagePOExample example);

    ImagePO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ImagePO record);

    int updateByPrimaryKey(ImagePO record);
}