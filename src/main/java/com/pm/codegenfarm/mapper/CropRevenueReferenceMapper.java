package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.CropRevenueReferenceRequestDTO;
import com.pm.codegenfarm.dto.response.CropRevenueReferenceResponseDTO;
import com.pm.codegenfarm.entity.CropRevenueReference;
import com.pm.codegenfarm.entity.Crop;
import com.pm.codegenfarm.repository.CropRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class CropRevenueReferenceMapper {

    private final CropRepository cropRepository;

    public CropRevenueReference toEntity(CropRevenueReferenceRequestDTO dto) {
        CropRevenueReference entity = CropRevenueReference.builder()
                .crop(cropRepository.findById(dto.getCropId())
                    .orElseThrow(() -> new NoSuchElementException("Crop not found")))
                .yieldPerHectare(dto.getYieldPerHectare())
                .unitPriceEstimate(dto.getUnitPriceEstimate())
                .revenueUnit(dto.getRevenueUnit())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public CropRevenueReferenceResponseDTO toDto(CropRevenueReference entity) {
        return CropRevenueReferenceResponseDTO.builder()
                .id(entity.getId())
                .cropId(entity.getCrop().getId())
                .yieldPerHectare(entity.getYieldPerHectare())
                .unitPriceEstimate(entity.getUnitPriceEstimate())
                .revenueUnit(entity.getRevenueUnit())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(CropRevenueReference entity, CropRevenueReferenceRequestDTO dto) {
        entity.setCrop(cropRepository.findById(dto.getCropId())
                .orElseThrow(() -> new NoSuchElementException("Crop not found")));
        entity.setYieldPerHectare(dto.getYieldPerHectare());
        entity.setUnitPriceEstimate(dto.getUnitPriceEstimate());
        entity.setRevenueUnit(dto.getRevenueUnit());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
