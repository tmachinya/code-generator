package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.InputItemRequestDTO;
import com.pm.codegenfarm.dto.response.InputItemResponseDTO;
import com.pm.codegenfarm.entity.InputItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class InputItemMapper {


    public InputItem toEntity(InputItemRequestDTO dto) {
        InputItem entity = InputItem.builder()
                .name(dto.getName())
                .type(dto.getType())
                .unit(dto.getUnit())
                .bufferLevel(dto.getBufferLevel())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public InputItemResponseDTO toDto(InputItem entity) {
        return InputItemResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .unit(entity.getUnit())
                .bufferLevel(entity.getBufferLevel())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(InputItem entity, InputItemRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setUnit(dto.getUnit());
        entity.setBufferLevel(dto.getBufferLevel());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
