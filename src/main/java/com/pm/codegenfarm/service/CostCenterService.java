package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.request.CostCenterRequestDTO;
import com.pm.codegenfarm.dto.response.CostCenterResponseDTO;
import java.util.List;

public interface CostCenterService {

    CostCenterResponseDTO create(CostCenterRequestDTO request);
    CostCenterResponseDTO update(Long id, CostCenterRequestDTO request);
    void delete(Long id);
    CostCenterResponseDTO getById(Long id);
    List<CostCenterResponseDTO> getAll();
}
