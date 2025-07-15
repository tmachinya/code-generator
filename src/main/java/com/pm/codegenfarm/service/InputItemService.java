package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.InputItemRequestDTO;
import com.pm.codegenfarm.dto.InputItemResponseDTO;
import java.util.List;

public interface InputItemService {

    InputItemResponseDTO create(InputItemRequestDTO request);
    InputItemResponseDTO update(Long id, InputItemRequestDTO request);
    void delete(Long id);
    InputItemResponseDTO getById(Long id);
    List<InputItemResponseDTO> getAll();
}
