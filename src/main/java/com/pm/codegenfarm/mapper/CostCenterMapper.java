package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.CostCenterRequestDTO;
import com.pm.codegenfarm.dto.response.CostCenterResponseDTO;
import com.pm.codegenfarm.entity.CostCenter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class CostCenterMapper {


    public CostCenter toEntity(CostCenterRequestDTO dto) {
        CostCenter entity = CostCenter.builder()
                .name(dto.getName())
                .sizeHectares(dto.getSizeHectares())
                .location(dto.getLocation())
                .soilType(dto.getSoilType())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public CostCenterResponseDTO toDto(CostCenter entity) {
        return CostCenterResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .sizeHectares(entity.getSizeHectares())
                .location(entity.getLocation())
                .soilType(entity.getSoilType())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(CostCenter entity, CostCenterRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setSizeHectares(dto.getSizeHectares());
        entity.setLocation(dto.getLocation());
        entity.setSoilType(dto.getSoilType());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
