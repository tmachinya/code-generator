package com.pm.codegenfarm.mapper;

import org.mapstruct.*;
import com.pm.codegenfarm.entity.LaborGrade;
import com.pm.codegenfarm.dto.LaborGradeRequestDTO;
import com.pm.codegenfarm.dto.LaborGradeResponseDTO;

@Mapper(componentModel = "spring")
public interface LaborGradeMapper {

    LaborGrade toEntity(LaborGradeRequestDTO dto);
    LaborGradeResponseDTO toResponseDto(LaborGrade entity);
    void updateEntity(@MappingTarget LaborGrade entity, LaborGradeRequestDTO dto);
}
