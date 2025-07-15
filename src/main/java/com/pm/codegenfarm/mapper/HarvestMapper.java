package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.Harvest;
import com.pm.codegenfarm.dto.HarvestRequestDTO;
import com.pm.codegenfarm.dto.HarvestResponseDTO;

@Mapper(componentModel = "spring")
public interface HarvestMapper {

    Harvest toEntity(HarvestRequestDTO dto);
    HarvestResponseDTO toResponseDto(Harvest entity);
    void updateEntity(@MappingTarget Harvest entity, HarvestRequestDTO dto);
}
