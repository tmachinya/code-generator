package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.Season;
import com.pm.codegenfarm.dto.SeasonRequestDTO;
import com.pm.codegenfarm.dto.SeasonResponseDTO;

@Mapper(componentModel = "spring")
public interface SeasonMapper {

    Season toEntity(SeasonRequestDTO dto);
    SeasonResponseDTO toResponseDto(Season entity);
    void updateEntity(@MappingTarget Season entity, SeasonRequestDTO dto);
}
