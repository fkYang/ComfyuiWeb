package com.yfk.comfyui.comfyui_dao.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.yfk.comfyui.comfyui_common.enums.RecordStatusEnum;
import com.yfk.comfyui.comfyui_common.utils.EnumConvertUtils;
import com.yfk.comfyui.comfyui_dao.dto.FlowRunRecordsDTO;
import com.yfk.comfyui.comfyui_dao.gen.domain.FlowRunRecordsPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 17:37
 */

@Mapper(imports = {JSONObject.class, TypeReference.class, List.class, Map.class,
        RecordStatusEnum.class, EnumConvertUtils.class, Objects.class,
})
public interface FlowRunRecordsMapper {
    FlowRunRecordsMapper INSTANCE = Mappers.getMapper(FlowRunRecordsMapper.class);

    @Mapping(target = "outputImage", expression = "java( JSONObject.parseObject(po.getOutputImage(), new TypeReference<List<Long>>() {}) )")
    @Mapping(target = "inputs", expression = "java( JSONObject.parseObject(po.getInputs(), new TypeReference<Map<String, String>>() {}) )")
    @Mapping(target = "status", expression = "java( EnumConvertUtils.toEnum(po.getStatus(), RecordStatusEnum.class))")
    FlowRunRecordsDTO poToDto(FlowRunRecordsPO po);

    @Mapping(target = "outputImage", expression = "java(  JSONObject.toJSONString(dto.getOutputImage()) )")
    @Mapping(target = "inputs", expression = "java( JSONObject.toJSONString(dto.getInputs()) )")
    @Mapping(target = "status", expression = "java( Objects.isNull(dto.getStatus())? " +
            "RecordStatusEnum.DONE.getCode():dto.getStatus().getCode() )")
    FlowRunRecordsPO dto2Po(FlowRunRecordsDTO dto);
}
