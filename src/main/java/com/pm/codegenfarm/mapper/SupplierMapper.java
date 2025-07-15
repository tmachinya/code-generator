package com.pm.codegenfarm.mapper;

import com.pm.codegenfarm.dto.request.SupplierRequestDTO;
import com.pm.codegenfarm.dto.response.SupplierResponseDTO;
import com.pm.codegenfarm.entity.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class SupplierMapper {


    public Supplier toEntity(SupplierRequestDTO dto) {
        Supplier entity = Supplier.builder()
                .name(dto.getName())
                .contactPerson(dto.getContactPerson())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .build();
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }

    public SupplierResponseDTO toDto(Supplier entity) {
        return SupplierResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .contactPerson(entity.getContactPerson())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .createdOn(entity.getCreatedOn())
                .createdBy(entity.getCreatedBy())
                .updatedOn(entity.getUpdatedOn())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public void updateEntity(Supplier entity, SupplierRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setContactPerson(dto.getContactPerson());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
        entity.setUpdatedBy(dto.getUpdatedBy());
    }
}
