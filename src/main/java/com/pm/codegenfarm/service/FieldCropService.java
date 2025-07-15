package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.FieldCropRequestDTO;
import com.pm.codegenfarm.dto.FieldCropResponseDTO;
import java.util.List;

public interface FieldCropService {

    FieldCropResponseDTO create(FieldCropRequestDTO request);
    FieldCropResponseDTO update(Long id, FieldCropRequestDTO request);
    void delete(Long id);
    FieldCropResponseDTO getById(Long id);
    List<FieldCropResponseDTO> getAll();
}
