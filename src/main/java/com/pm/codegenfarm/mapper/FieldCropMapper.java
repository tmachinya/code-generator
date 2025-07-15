package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.FieldCropRequestDTO;
import com.pm.codegenfarm.dto.response.FieldCropResponseDTO;
import com.pm.codegenfarm.entity.FieldCrop;
import com.pm.codegenfarm.entity.CostCenter;
import com.pm.codegenfarm.repository.CostCenterRepository;
import com.pm.codegenfarm.entity.Crop;
import com.pm.codegenfarm.repository.CropRepository;
import com.pm.codegenfarm.entity.Season;
import com.pm.codegenfarm.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class FieldCropMapper {

    private final CostCenterRepository costCenterRepository;
    private final CropRepository cropRepository;
    private final SeasonRepository seasonRepository;

    public FieldCrop toEntity(FieldCropRequestDTO dto) {
        FieldCrop entity = FieldCrop.builder()
                .costCenter(costCenterRepository.findById(dto.getCostCenterId())
                    .orElseThrow(() -> new NoSuchElementException("CostCenter not found")))
                .crop(cropRepository.findById(dto.getCropId())
                    .orElseThrow(() -> new NoSuchElementException("Crop not found")))
                .season(seasonRepository.findById(dto.getSeasonId())
                    .orElseThrow(() -> new NoSuchElementException("Season not found")))
                .hectaresAllocated(dto.getHectaresAllocated())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public FieldCropResponseDTO toDto(FieldCrop entity) {
        return FieldCropResponseDTO.builder()
                .id(entity.getId())
                .costCenterId(entity.getCostCenter().getId())
                .cropId(entity.getCrop().getId())
                .seasonId(entity.getSeason().getId())
                .hectaresAllocated(entity.getHectaresAllocated())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(FieldCrop entity, FieldCropRequestDTO dto) {
        entity.setCostCenter(costCenterRepository.findById(dto.getCostCenterId())
                .orElseThrow(() -> new NoSuchElementException("CostCenter not found")));
        entity.setCrop(cropRepository.findById(dto.getCropId())
                .orElseThrow(() -> new NoSuchElementException("Crop not found")));
        entity.setSeason(seasonRepository.findById(dto.getSeasonId())
                .orElseThrow(() -> new NoSuchElementException("Season not found")));
        entity.setHectaresAllocated(dto.getHectaresAllocated());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
