package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.ActivityInput;
import com.pm.codegenfarm.dto.ActivityInputRequestDTO;
import com.pm.codegenfarm.dto.ActivityInputResponseDTO;

@Mapper(componentModel = "spring")
public interface ActivityInputMapper {

    ActivityInput toEntity(ActivityInputRequestDTO dto);
    ActivityInputResponseDTO toResponseDto(ActivityInput entity);
    void updateEntity(@MappingTarget ActivityInput entity, ActivityInputRequestDTO dto);
}
