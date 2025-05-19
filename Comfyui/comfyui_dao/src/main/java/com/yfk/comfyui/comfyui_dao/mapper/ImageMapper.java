package com.yfk.comfyui.comfyui_dao.mapper;

import com.yfk.comfyui.comfyui_common.enums.ImageSourceTypeEnum;
import com.yfk.comfyui.comfyui_common.utils.EnumConvertUtils;
import com.yfk.comfyui.comfyui_dao.dto.ImageDTO;
import com.yfk.comfyui.comfyui_dao.gen.domain.ImagePO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

/**
 * desc：
 * author：fkYang
 * date： 2025/5/15 16:06
 */
@Mapper( imports = {Objects.class, EnumConvertUtils.class, ImageSourceTypeEnum.class })
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    @Mapping(target = "imageTypeEnum", expression = "java( EnumConvertUtils.toEnum(po.getImageType(), ImageSourceTypeEnum.class))")
    ImageDTO poToDto(ImagePO po);

    @Mapping(target = "imageType", expression = "java( Objects.isNull(dto.getImageTypeEnum())? " +
            "ImageSourceTypeEnum.USER_UPLOAD.getCode():dto.getImageTypeEnum().getCode() )")
    ImagePO dtoToPo(ImageDTO dto);
}
