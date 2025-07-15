package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.CropInputReference;
import com.pm.codegenfarm.dto.CropInputReferenceRequestDTO;
import com.pm.codegenfarm.dto.CropInputReferenceResponseDTO;

@Mapper(componentModel = "spring")
public interface CropInputReferenceMapper {

    CropInputReference toEntity(CropInputReferenceRequestDTO dto);
    CropInputReferenceResponseDTO toResponseDto(CropInputReference entity);
    void updateEntity(@MappingTarget CropInputReference entity, CropInputReferenceRequestDTO dto);
}
