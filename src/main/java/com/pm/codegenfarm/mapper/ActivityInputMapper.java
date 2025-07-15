package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.ActivityInputRequestDTO;
import com.pm.codegenfarm.dto.response.ActivityInputResponseDTO;
import com.pm.codegenfarm.entity.ActivityInput;
import com.pm.codegenfarm.entity.Activity;
import com.pm.codegenfarm.repository.ActivityRepository;
import com.pm.codegenfarm.entity.InputItem;
import com.pm.codegenfarm.repository.InputItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class ActivityInputMapper {

    private final ActivityRepository activityRepository;
    private final InputItemRepository inputItemRepository;

    public ActivityInput toEntity(ActivityInputRequestDTO dto) {
        ActivityInput entity = ActivityInput.builder()
                .activity(activityRepository.findById(dto.getActivityId())
                    .orElseThrow(() -> new NoSuchElementException("Activity not found")))
                .inputItem(inputItemRepository.findById(dto.getInputItemId())
                    .orElseThrow(() -> new NoSuchElementException("InputItem not found")))
                .quantityUsed(dto.getQuantityUsed())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public ActivityInputResponseDTO toDto(ActivityInput entity) {
        return ActivityInputResponseDTO.builder()
                .id(entity.getId())
                .activityId(entity.getActivity().getId())
                .inputItemId(entity.getInputItem().getId())
                .quantityUsed(entity.getQuantityUsed())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(ActivityInput entity, ActivityInputRequestDTO dto) {
        entity.setActivity(activityRepository.findById(dto.getActivityId())
                .orElseThrow(() -> new NoSuchElementException("Activity not found")));
        entity.setInputItem(inputItemRepository.findById(dto.getInputItemId())
                .orElseThrow(() -> new NoSuchElementException("InputItem not found")));
        entity.setQuantityUsed(dto.getQuantityUsed());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
