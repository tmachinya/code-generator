package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.CropInputReferenceRequestDTO;
import com.pm.codegenfarm.dto.response.CropInputReferenceResponseDTO;
import com.pm.codegenfarm.entity.CropInputReference;
import com.pm.codegenfarm.entity.Crop;
import com.pm.codegenfarm.repository.CropRepository;
import com.pm.codegenfarm.entity.InputItem;
import com.pm.codegenfarm.repository.InputItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class CropInputReferenceMapper {

    private final CropRepository cropRepository;
    private final InputItemRepository inputItemRepository;

    public CropInputReference toEntity(CropInputReferenceRequestDTO dto) {
        CropInputReference entity = CropInputReference.builder()
                .crop(cropRepository.findById(dto.getCropId())
                    .orElseThrow(() -> new NoSuchElementException("Crop not found")))
                .inputItem(inputItemRepository.findById(dto.getInputItemId())
                    .orElseThrow(() -> new NoSuchElementException("InputItem not found")))
                .quantityPerHectare(dto.getQuantityPerHectare())
                .unitCostEstimate(dto.getUnitCostEstimate())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public CropInputReferenceResponseDTO toDto(CropInputReference entity) {
        return CropInputReferenceResponseDTO.builder()
                .id(entity.getId())
                .cropId(entity.getCrop().getId())
                .inputItemId(entity.getInputItem().getId())
                .quantityPerHectare(entity.getQuantityPerHectare())
                .unitCostEstimate(entity.getUnitCostEstimate())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(CropInputReference entity, CropInputReferenceRequestDTO dto) {
        entity.setCrop(cropRepository.findById(dto.getCropId())
                .orElseThrow(() -> new NoSuchElementException("Crop not found")));
        entity.setInputItem(inputItemRepository.findById(dto.getInputItemId())
                .orElseThrow(() -> new NoSuchElementException("InputItem not found")));
        entity.setQuantityPerHectare(dto.getQuantityPerHectare());
        entity.setUnitCostEstimate(dto.getUnitCostEstimate());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
