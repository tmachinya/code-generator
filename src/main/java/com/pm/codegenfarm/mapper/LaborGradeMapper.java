package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.LaborGradeRequestDTO;
import com.pm.codegenfarm.dto.response.LaborGradeResponseDTO;
import com.pm.codegenfarm.entity.LaborGrade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class LaborGradeMapper {


    public LaborGrade toEntity(LaborGradeRequestDTO dto) {
        LaborGrade entity = LaborGrade.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .wageType(dto.getWageType())
                .dailyRate(dto.getDailyRate())
                .monthlySalary(dto.getMonthlySalary())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public LaborGradeResponseDTO toDto(LaborGrade entity) {
        return LaborGradeResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .wageType(entity.getWageType())
                .dailyRate(entity.getDailyRate())
                .monthlySalary(entity.getMonthlySalary())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(LaborGrade entity, LaborGradeRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setWageType(dto.getWageType());
        entity.setDailyRate(dto.getDailyRate());
        entity.setMonthlySalary(dto.getMonthlySalary());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
