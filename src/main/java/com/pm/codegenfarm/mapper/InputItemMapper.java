package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.InputItem;
import com.pm.codegenfarm.dto.InputItemRequestDTO;
import com.pm.codegenfarm.dto.InputItemResponseDTO;

@Mapper(componentModel = "spring")
public interface InputItemMapper {

    InputItem toEntity(InputItemRequestDTO dto);
    InputItemResponseDTO toResponseDto(InputItem entity);
    void updateEntity(@MappingTarget InputItem entity, InputItemRequestDTO dto);
}
