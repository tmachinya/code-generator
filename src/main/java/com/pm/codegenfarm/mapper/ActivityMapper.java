package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.ActivityRequestDTO;
import com.pm.codegenfarm.dto.response.ActivityResponseDTO;
import com.pm.codegenfarm.entity.Activity;
import com.pm.codegenfarm.entity.CostCenter;
import com.pm.codegenfarm.repository.CostCenterRepository;
import com.pm.codegenfarm.entity.Season;
import com.pm.codegenfarm.repository.SeasonRepository;
import com.pm.codegenfarm.entity.Crop;
import com.pm.codegenfarm.repository.CropRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class ActivityMapper {

    private final CostCenterRepository costCenterRepository;
    private final SeasonRepository seasonRepository;
    private final CropRepository cropRepository;

    public Activity toEntity(ActivityRequestDTO dto) {
        Activity entity = Activity.builder()
                .type(dto.getType())
                .date(dto.getDate())
                .costCenter(costCenterRepository.findById(dto.getCostCenterId())
                    .orElseThrow(() -> new NoSuchElementException("CostCenter not found")))
                .season(seasonRepository.findById(dto.getSeasonId())
                    .orElseThrow(() -> new NoSuchElementException("Season not found")))
                .crop(cropRepository.findById(dto.getCropId())
                    .orElseThrow(() -> new NoSuchElementException("Crop not found")))
                .description(dto.getDescription())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public ActivityResponseDTO toDto(Activity entity) {
        return ActivityResponseDTO.builder()
                .id(entity.getId())
                .type(entity.getType())
                .date(entity.getDate())
                .costCenterId(entity.getCostCenter().getId())
                .seasonId(entity.getSeason().getId())
                .cropId(entity.getCrop().getId())
                .description(entity.getDescription())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(Activity entity, ActivityRequestDTO dto) {
        entity.setType(dto.getType());
        entity.setDate(dto.getDate());
        entity.setCostCenter(costCenterRepository.findById(dto.getCostCenterId())
                .orElseThrow(() -> new NoSuchElementException("CostCenter not found")));
        entity.setSeason(seasonRepository.findById(dto.getSeasonId())
                .orElseThrow(() -> new NoSuchElementException("Season not found")));
        entity.setCrop(cropRepository.findById(dto.getCropId())
                .orElseThrow(() -> new NoSuchElementException("Crop not found")));
        entity.setDescription(dto.getDescription());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
