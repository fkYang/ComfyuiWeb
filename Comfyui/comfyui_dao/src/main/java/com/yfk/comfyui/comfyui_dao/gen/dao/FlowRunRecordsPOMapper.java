package com.yfk.comfyui.comfyui_dao.gen.dao;

import com.yfk.comfyui.comfyui_dao.gen.domain.FlowRunRecordsPO;

public interface FlowRunRecordsPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FlowRunRecordsPO record);

    int insertSelective(FlowRunRecordsPO record);

    FlowRunRecordsPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FlowRunRecordsPO record);

    int updateByPrimaryKey(FlowRunRecordsPO record);
}