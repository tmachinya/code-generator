package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.request.CropRequestDTO;
import com.pm.codegenfarm.dto.response.CropResponseDTO;
import java.util.List;

public interface CropService {

    CropResponseDTO create(CropRequestDTO request);
    CropResponseDTO update(Long id, CropRequestDTO request);
    void delete(Long id);
    CropResponseDTO getById(Long id);
    List<CropResponseDTO> getAll();
}
