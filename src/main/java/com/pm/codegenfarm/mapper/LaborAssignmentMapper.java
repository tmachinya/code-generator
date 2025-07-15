package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.LaborAssignmentRequestDTO;
import com.pm.codegenfarm.dto.response.LaborAssignmentResponseDTO;
import com.pm.codegenfarm.entity.LaborAssignment;
import com.pm.codegenfarm.entity.Labor;
import com.pm.codegenfarm.repository.LaborRepository;
import com.pm.codegenfarm.entity.CostCenter;
import com.pm.codegenfarm.repository.CostCenterRepository;
import com.pm.codegenfarm.entity.Season;
import com.pm.codegenfarm.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class LaborAssignmentMapper {

    private final LaborRepository laborRepository;
    private final CostCenterRepository costCenterRepository;
    private final SeasonRepository seasonRepository;

    public LaborAssignment toEntity(LaborAssignmentRequestDTO dto) {
        LaborAssignment entity = LaborAssignment.builder()
                .labor(laborRepository.findById(dto.getLaborId())
                    .orElseThrow(() -> new NoSuchElementException("Labor not found")))
                .activityId(dto.getActivityId())
                .costCenter(costCenterRepository.findById(dto.getCostCenterId())
                    .orElseThrow(() -> new NoSuchElementException("CostCenter not found")))
                .season(seasonRepository.findById(dto.getSeasonId())
                    .orElseThrow(() -> new NoSuchElementException("Season not found")))
                .daysWorked(dto.getDaysWorked())
                .taskDescription(dto.getTaskDescription())
                .dateAssigned(dto.getDateAssigned())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public LaborAssignmentResponseDTO toDto(LaborAssignment entity) {
        return LaborAssignmentResponseDTO.builder()
                .id(entity.getId())
                .laborId(entity.getLabor().getId())
                .activityId(entity.getActivityId())
                .costCenterId(entity.getCostCenter().getId())
                .seasonId(entity.getSeason().getId())
                .daysWorked(entity.getDaysWorked())
                .taskDescription(entity.getTaskDescription())
                .dateAssigned(entity.getDateAssigned())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(LaborAssignment entity, LaborAssignmentRequestDTO dto) {
        entity.setLabor(laborRepository.findById(dto.getLaborId())
                .orElseThrow(() -> new NoSuchElementException("Labor not found")));
        entity.setActivityId(dto.getActivityId());
        entity.setCostCenter(costCenterRepository.findById(dto.getCostCenterId())
                .orElseThrow(() -> new NoSuchElementException("CostCenter not found")));
        entity.setSeason(seasonRepository.findById(dto.getSeasonId())
                .orElseThrow(() -> new NoSuchElementException("Season not found")));
        entity.setDaysWorked(dto.getDaysWorked());
        entity.setTaskDescription(dto.getTaskDescription());
        entity.setDateAssigned(dto.getDateAssigned());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
