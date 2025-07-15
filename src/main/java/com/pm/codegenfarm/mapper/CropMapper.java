package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.Crop;
import com.pm.codegenfarm.dto.CropRequestDTO;
import com.pm.codegenfarm.dto.CropResponseDTO;

@Mapper(componentModel = "spring")
public interface CropMapper {

    Crop toEntity(CropRequestDTO dto);
    CropResponseDTO toResponseDto(Crop entity);
    void updateEntity(@MappingTarget Crop entity, CropRequestDTO dto);
}
