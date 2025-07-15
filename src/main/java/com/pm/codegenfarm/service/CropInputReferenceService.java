package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.request.CropInputReferenceRequestDTO;
import com.pm.codegenfarm.dto.response.CropInputReferenceResponseDTO;
import java.util.List;

public interface CropInputReferenceService {

    CropInputReferenceResponseDTO create(CropInputReferenceRequestDTO request);
    CropInputReferenceResponseDTO update(Long id, CropInputReferenceRequestDTO request);
    void delete(Long id);
    CropInputReferenceResponseDTO getById(Long id);
    List<CropInputReferenceResponseDTO> getAll();
}
