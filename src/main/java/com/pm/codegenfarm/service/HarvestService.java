package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.request.HarvestRequestDTO;
import com.pm.codegenfarm.dto.response.HarvestResponseDTO;
import java.util.List;

public interface HarvestService {

    HarvestResponseDTO create(HarvestRequestDTO request);
    HarvestResponseDTO update(Long id, HarvestRequestDTO request);
    void delete(Long id);
    HarvestResponseDTO getById(Long id);
    List<HarvestResponseDTO> getAll();
}
