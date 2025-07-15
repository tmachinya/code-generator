package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.CropRevenueReference;
import com.pm.codegenfarm.dto.CropRevenueReferenceRequestDTO;
import com.pm.codegenfarm.dto.CropRevenueReferenceResponseDTO;

@Mapper(componentModel = "spring")
public interface CropRevenueReferenceMapper {

    CropRevenueReference toEntity(CropRevenueReferenceRequestDTO dto);
    CropRevenueReferenceResponseDTO toResponseDto(CropRevenueReference entity);
    void updateEntity(@MappingTarget CropRevenueReference entity, CropRevenueReferenceRequestDTO dto);
}
