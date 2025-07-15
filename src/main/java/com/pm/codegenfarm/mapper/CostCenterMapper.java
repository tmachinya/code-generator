package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.CostCenter;
import com.pm.codegenfarm.dto.CostCenterRequestDTO;
import com.pm.codegenfarm.dto.CostCenterResponseDTO;

@Mapper(componentModel = "spring")
public interface CostCenterMapper {

    CostCenter toEntity(CostCenterRequestDTO dto);
    CostCenterResponseDTO toResponseDto(CostCenter entity);
    void updateEntity(@MappingTarget CostCenter entity, CostCenterRequestDTO dto);
}
