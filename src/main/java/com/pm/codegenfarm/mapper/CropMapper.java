package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.CropRequestDTO;
import com.pm.codegenfarm.dto.response.CropResponseDTO;
import com.pm.codegenfarm.entity.Crop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class CropMapper {


    public Crop toEntity(CropRequestDTO dto) {
        Crop entity = Crop.builder()
                .name(dto.getName())
                .defaultYieldPerHa(dto.getDefaultYieldPerHa())
                .durationDays(dto.getDurationDays())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public CropResponseDTO toDto(Crop entity) {
        return CropResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .defaultYieldPerHa(entity.getDefaultYieldPerHa())
                .durationDays(entity.getDurationDays())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(Crop entity, CropRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setDefaultYieldPerHa(dto.getDefaultYieldPerHa());
        entity.setDurationDays(dto.getDurationDays());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
