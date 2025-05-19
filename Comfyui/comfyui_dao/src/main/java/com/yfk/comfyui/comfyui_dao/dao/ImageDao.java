package com.yfk.comfyui.comfyui_dao.dao;

import com.yfk.comfyui.comfyui_dao.dto.ImageDTO;
import com.yfk.comfyui.comfyui_dao.gen.dao.ImagePOMapper;
import com.yfk.comfyui.comfyui_dao.gen.domain.FlowPO;
import com.yfk.comfyui.comfyui_dao.gen.domain.FlowPOExample;
import com.yfk.comfyui.comfyui_dao.gen.domain.ImagePO;
import com.yfk.comfyui.comfyui_dao.gen.domain.ImagePOExample;
import com.yfk.comfyui.comfyui_dao.mapper.FlowMapper;
import com.yfk.comfyui.comfyui_dao.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 16:50
 */
@Repository
public class ImageDao {
    @Autowired
    private ImagePOMapper mapper;

    public ImageDTO searchById(Long id) {
        ImagePO imagePO = mapper.selectByPrimaryKey(id);
        return ImageMapper.INSTANCE.poToDto(imagePO);
    }
    public List<ImageDTO> searchByIds(List<Long> ids){
        if(CollectionUtils.isEmpty(ids)){
            return new ArrayList<>();
        }
        ImagePOExample example = new ImagePOExample();
        ImagePOExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        List<ImagePO> pos = mapper.selectByExample(example);
        return pos.stream().map(ImageMapper.INSTANCE::poToDto).toList();
    }

    public void insert(ImageDTO dto) {
        ImagePO po = ImageMapper.INSTANCE.dtoToPo(dto);
        mapper.insertSelective(po);
        //System.out.println(po.getId());
        dto.setId(po.getId());
    }
}
