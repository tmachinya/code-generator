package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.Supplier;
import com.pm.codegenfarm.dto.SupplierRequestDTO;
import com.pm.codegenfarm.dto.SupplierResponseDTO;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    Supplier toEntity(SupplierRequestDTO dto);
    SupplierResponseDTO toResponseDto(Supplier entity);
    void updateEntity(@MappingTarget Supplier entity, SupplierRequestDTO dto);
}
