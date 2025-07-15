package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.request.SaleRequestDTO;
import com.pm.codegenfarm.dto.response.SaleResponseDTO;
import java.util.List;

public interface SaleService {

    SaleResponseDTO create(SaleRequestDTO request);
    SaleResponseDTO update(Long id, SaleRequestDTO request);
    void delete(Long id);
    SaleResponseDTO getById(Long id);
    List<SaleResponseDTO> getAll();
}
