package com.yfk.comfyui.comfyui_web.mapper;

import com.yfk.comfyui.comfyui_dao.dto.FlowDTO;
import com.yfk.comfyui.comfyui_dao.dto.field.FlowInputDTO;
import com.yfk.comfyui.comfyui_web.dto.FlowResponse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/17 12:17
 */
@Mapper(imports = {FlowDTO.class, FlowInputDTO.class, FlowResponse.class, List.class})
public interface FlowResponseMapper {
    FlowResponseMapper INSTANCE = Mappers.getMapper(FlowResponseMapper.class);

    //@Mapping(target = "inputNodes", expression = "java( dto.getFlowInputs().stream().map(this::inputConvert).toList() )")

    List<FlowResponse> flowListConvert(List<FlowDTO> dtos);


    @Mapping(target = "inputNodes", expression = "java( dto.getFlowInputs().stream().map(this::inputConvert).toList() )")
    FlowResponse flowConvert(FlowDTO dto) ;

    FlowResponse.InputNode  inputConvert(FlowInputDTO dto);
}
