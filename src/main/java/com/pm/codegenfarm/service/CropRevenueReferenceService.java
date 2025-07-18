package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.request.CropRevenueReferenceRequestDTO;
import com.pm.codegenfarm.dto.response.CropRevenueReferenceResponseDTO;
import java.util.List;

public interface CropRevenueReferenceService {

    CropRevenueReferenceResponseDTO create(CropRevenueReferenceRequestDTO request);
    CropRevenueReferenceResponseDTO update(Long id, CropRevenueReferenceRequestDTO request);
    void delete(Long id);
    CropRevenueReferenceResponseDTO getById(Long id);
    List<CropRevenueReferenceResponseDTO> getAll();
}
