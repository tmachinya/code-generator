package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.HarvestRequestDTO;
import com.pm.codegenfarm.dto.response.HarvestResponseDTO;
import com.pm.codegenfarm.entity.Harvest;
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
public class HarvestMapper {

    private final CostCenterRepository costCenterRepository;
    private final CropRepository cropRepository;
    private final SeasonRepository seasonRepository;

    public Harvest toEntity(HarvestRequestDTO dto) {
        Harvest entity = Harvest.builder()
                .costCenter(costCenterRepository.findById(dto.getCostCenterId())
                    .orElseThrow(() -> new NoSuchElementException("CostCenter not found")))
                .crop(cropRepository.findById(dto.getCropId())
                    .orElseThrow(() -> new NoSuchElementException("Crop not found")))
                .season(seasonRepository.findById(dto.getSeasonId())
                    .orElseThrow(() -> new NoSuchElementException("Season not found")))
                .harvestDate(dto.getHarvestDate())
                .yieldQty(dto.getYieldQty())
                .unit(dto.getUnit())
                .notes(dto.getNotes())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public HarvestResponseDTO toDto(Harvest entity) {
        return HarvestResponseDTO.builder()
                .id(entity.getId())
                .costCenterId(entity.getCostCenter().getId())
                .cropId(entity.getCrop().getId())
                .seasonId(entity.getSeason().getId())
                .harvestDate(entity.getHarvestDate())
                .yieldQty(entity.getYieldQty())
                .unit(entity.getUnit())
                .notes(entity.getNotes())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(Harvest entity, HarvestRequestDTO dto) {
        entity.setCostCenter(costCenterRepository.findById(dto.getCostCenterId())
                .orElseThrow(() -> new NoSuchElementException("CostCenter not found")));
        entity.setCrop(cropRepository.findById(dto.getCropId())
                .orElseThrow(() -> new NoSuchElementException("Crop not found")));
        entity.setSeason(seasonRepository.findById(dto.getSeasonId())
                .orElseThrow(() -> new NoSuchElementException("Season not found")));
        entity.setHarvestDate(dto.getHarvestDate());
        entity.setYieldQty(dto.getYieldQty());
        entity.setUnit(dto.getUnit());
        entity.setNotes(dto.getNotes());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
