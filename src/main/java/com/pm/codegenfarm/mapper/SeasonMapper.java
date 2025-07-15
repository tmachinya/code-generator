package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.SeasonRequestDTO;
import com.pm.codegenfarm.dto.response.SeasonResponseDTO;
import com.pm.codegenfarm.entity.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class SeasonMapper {


    public Season toEntity(SeasonRequestDTO dto) {
        Season entity = Season.builder()
                .name(dto.getName())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public SeasonResponseDTO toDto(Season entity) {
        return SeasonResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(Season entity, SeasonRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
