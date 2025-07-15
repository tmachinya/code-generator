package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.SupplierRequestDTO;
import com.pm.codegenfarm.dto.SupplierResponseDTO;
import java.util.List;

public interface SupplierService {

    SupplierResponseDTO create(SupplierRequestDTO request);
    SupplierResponseDTO update(Long id, SupplierRequestDTO request);
    void delete(Long id);
    SupplierResponseDTO getById(Long id);
    List<SupplierResponseDTO> getAll();
}
