package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.Sale;
import com.pm.codegenfarm.dto.SaleRequestDTO;
import com.pm.codegenfarm.dto.SaleResponseDTO;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    Sale toEntity(SaleRequestDTO dto);
    SaleResponseDTO toResponseDto(Sale entity);
    void updateEntity(@MappingTarget Sale entity, SaleRequestDTO dto);
}
