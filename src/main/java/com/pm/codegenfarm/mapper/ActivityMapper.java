package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.Activity;
import com.pm.codegenfarm.dto.ActivityRequestDTO;
import com.pm.codegenfarm.dto.ActivityResponseDTO;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    Activity toEntity(ActivityRequestDTO dto);
    ActivityResponseDTO toResponseDto(Activity entity);
    void updateEntity(@MappingTarget Activity entity, ActivityRequestDTO dto);
}
