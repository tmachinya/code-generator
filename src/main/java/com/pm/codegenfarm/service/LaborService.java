package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.request.LaborRequestDTO;
import com.pm.codegenfarm.dto.response.LaborResponseDTO;
import java.util.List;

public interface LaborService {

    LaborResponseDTO create(LaborRequestDTO request);
    LaborResponseDTO update(Long id, LaborRequestDTO request);
    void delete(Long id);
    LaborResponseDTO getById(Long id);
    List<LaborResponseDTO> getAll();
}
