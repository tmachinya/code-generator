package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.LaborRequestDTO;
import com.pm.codegenfarm.dto.response.LaborResponseDTO;
import com.pm.codegenfarm.entity.Labor;
import com.pm.codegenfarm.entity.LaborGrade;
import com.pm.codegenfarm.repository.LaborGradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class LaborMapper {

    private final LaborGradeRepository laborGradeRepository;

    public Labor toEntity(LaborRequestDTO dto) {
        Labor entity = Labor.builder()
                .fullName(dto.getFullName())
                .nationalId(dto.getNationalId())
                .contactNumber(dto.getContactNumber())
                .employmentType(dto.getEmploymentType())
                .laborGrade(laborGradeRepository.findById(dto.getGradeId())
                    .orElseThrow(() -> new NoSuchElementException("LaborGrade not found")))
                .dateJoined(dto.getDateJoined())
                .isActive(dto.getIsActive())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public LaborResponseDTO toDto(Labor entity) {
        return LaborResponseDTO.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .nationalId(entity.getNationalId())
                .contactNumber(entity.getContactNumber())
                .employmentType(entity.getEmploymentType())
                .gradeId(entity.getLaborGrade().getId())
                .dateJoined(entity.getDateJoined())
                .isActive(entity.getIsActive())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(Labor entity, LaborRequestDTO dto) {
        entity.setFullName(dto.getFullName());
        entity.setNationalId(dto.getNationalId());
        entity.setContactNumber(dto.getContactNumber());
        entity.setEmploymentType(dto.getEmploymentType());
        entity.setLaborGrade(laborGradeRepository.findById(dto.getGradeId())
                .orElseThrow(() -> new NoSuchElementException("LaborGrade not found")));
        entity.setDateJoined(dto.getDateJoined());
        entity.setIsActive(dto.getIsActive());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
