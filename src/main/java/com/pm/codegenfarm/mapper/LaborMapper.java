package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.Labor;
import com.pm.codegenfarm.dto.LaborRequestDTO;
import com.pm.codegenfarm.dto.LaborResponseDTO;

@Mapper(componentModel = "spring")
public interface LaborMapper {

    Labor toEntity(LaborRequestDTO dto);
    LaborResponseDTO toResponseDto(Labor entity);
    void updateEntity(@MappingTarget Labor entity, LaborRequestDTO dto);
}
