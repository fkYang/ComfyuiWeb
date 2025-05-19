package com.yfk.comfyui.comfyui_dao.dao;

import com.yfk.comfyui.comfyui_dao.dto.FlowRunRecordsDTO;
import com.yfk.comfyui.comfyui_dao.gen.dao.FlowRunRecordsPOMapper;
import com.yfk.comfyui.comfyui_dao.gen.domain.FlowRunRecordsPO;
import com.yfk.comfyui.comfyui_dao.mapper.FlowRunRecordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 17:36
 */

@Repository
public class FlowRunRecordDao {
    @Autowired
    FlowRunRecordsPOMapper mapper;

    public FlowRunRecordsDTO searchById(Long id){
        FlowRunRecordsPO po = mapper.selectByPrimaryKey(id);
        return FlowRunRecordsMapper.INSTANCE.poToDto(po);
    }


    public void insert(FlowRunRecordsDTO dto){
        FlowRunRecordsPO po = FlowRunRecordsMapper.INSTANCE.dto2Po(dto);
        mapper.insert(po);
        dto.setId(po.getId());
    }

    public void save(FlowRunRecordsDTO dto){
        FlowRunRecordsPO po = FlowRunRecordsMapper.INSTANCE.dto2Po(dto);
        mapper.updateByPrimaryKeySelective(po);
        //dto.setId(po.getId());
    }

}
