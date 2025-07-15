package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.LaborAssignment;
import com.pm.codegenfarm.dto.LaborAssignmentRequestDTO;
import com.pm.codegenfarm.dto.LaborAssignmentResponseDTO;

@Mapper(componentModel = "spring")
public interface LaborAssignmentMapper {

    LaborAssignment toEntity(LaborAssignmentRequestDTO dto);
    LaborAssignmentResponseDTO toResponseDto(LaborAssignment entity);
    void updateEntity(@MappingTarget LaborAssignment entity, LaborAssignmentRequestDTO dto);
}
