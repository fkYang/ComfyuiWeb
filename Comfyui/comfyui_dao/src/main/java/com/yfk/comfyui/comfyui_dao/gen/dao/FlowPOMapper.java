package com.yfk.comfyui.comfyui_dao.gen.dao;

import com.yfk.comfyui.comfyui_dao.gen.domain.FlowPO;
import com.yfk.comfyui.comfyui_dao.gen.domain.FlowPOExample;
import java.util.List;

public interface FlowPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowPO record);

    int insertSelective(FlowPO record);

    List<FlowPO> selectByExampleWithBLOBs(FlowPOExample example);

    List<FlowPO> selectByExample(FlowPOExample example);

    FlowPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowPO record);

    int updateByPrimaryKeyWithBLOBs(FlowPO record);

    int updateByPrimaryKey(FlowPO record);
}