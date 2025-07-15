package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.SaleRequestDTO;
import com.pm.codegenfarm.dto.SaleResponseDTO;
import java.util.List;

public interface SaleService {

    SaleResponseDTO create(SaleRequestDTO request);
    SaleResponseDTO update(Long id, SaleRequestDTO request);
    void delete(Long id);
    SaleResponseDTO getById(Long id);
    List<SaleResponseDTO> getAll();
}
