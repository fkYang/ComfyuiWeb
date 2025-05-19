package com.yfk.comfyui.comfyui_dao.mapper;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.yfk.comfyui.comfyui_dao.dto.FlowDTO;
import com.yfk.comfyui.comfyui_dao.dto.field.FlowInputDTO;
import com.yfk.comfyui.comfyui_dao.gen.domain.FlowPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/16 16:21
 */

@Mapper( imports = {JSONObject.class, TypeReference.class, FlowInputDTO.class, List.class })
public interface FlowMapper {

    FlowMapper INSTANCE = Mappers.getMapper(FlowMapper.class);

    @Mapping(target = "flowInputs", expression = "java( JSONObject.parseObject(po.getFlowInputs(), new TypeReference<List<FlowInputDTO>>() {}) )")
    FlowDTO poToDto(FlowPO po);

//    @Mapping(target = "imageType", expression = "java( Objects.isNull(dto.getImageTypeEnum())? " +
//            "ImageTypeEnum.USER_UPLOAD.getCode():dto.getImageTypeEnum().getCode() )")
//    FlowPO dtoToPo(FlowDTO dto);
}
