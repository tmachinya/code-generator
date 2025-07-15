package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.FieldCrop;
import com.pm.codegenfarm.dto.FieldCropRequestDTO;
import com.pm.codegenfarm.dto.FieldCropResponseDTO;

@Mapper(componentModel = "spring")
public interface FieldCropMapper {

    FieldCrop toEntity(FieldCropRequestDTO dto);
    FieldCropResponseDTO toResponseDto(FieldCrop entity);
    void updateEntity(@MappingTarget FieldCrop entity, FieldCropRequestDTO dto);
}
