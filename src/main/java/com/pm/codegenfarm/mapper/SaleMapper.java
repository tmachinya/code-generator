package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.SaleRequestDTO;
import com.pm.codegenfarm.dto.response.SaleResponseDTO;
import com.pm.codegenfarm.entity.Sale;
import com.pm.codegenfarm.entity.Harvest;
import com.pm.codegenfarm.repository.HarvestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class SaleMapper {

    private final HarvestRepository harvestRepository;

    public Sale toEntity(SaleRequestDTO dto) {
        Sale entity = Sale.builder()
                .harvest(harvestRepository.findById(dto.getHarvestId())
                    .orElseThrow(() -> new NoSuchElementException("Harvest not found")))
                .buyer(dto.getBuyer())
                .quantitySold(dto.getQuantitySold())
                .unitPrice(dto.getUnitPrice())
                .totalAmount(dto.getTotalAmount())
                .saleDate(dto.getSaleDate())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public SaleResponseDTO toDto(Sale entity) {
        return SaleResponseDTO.builder()
                .id(entity.getId())
                .harvestId(entity.getHarvest().getId())
                .buyer(entity.getBuyer())
                .quantitySold(entity.getQuantitySold())
                .unitPrice(entity.getUnitPrice())
                .totalAmount(entity.getTotalAmount())
                .saleDate(entity.getSaleDate())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(Sale entity, SaleRequestDTO dto) {
        entity.setHarvest(harvestRepository.findById(dto.getHarvestId())
                .orElseThrow(() -> new NoSuchElementException("Harvest not found")));
        entity.setBuyer(dto.getBuyer());
        entity.setQuantitySold(dto.getQuantitySold());
        entity.setUnitPrice(dto.getUnitPrice());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setSaleDate(dto.getSaleDate());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
