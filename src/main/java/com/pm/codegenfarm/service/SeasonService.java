package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.SeasonRequestDTO;
import com.pm.codegenfarm.dto.SeasonResponseDTO;
import java.util.List;

public interface SeasonService {

    SeasonResponseDTO create(SeasonRequestDTO request);
    SeasonResponseDTO update(Long id, SeasonRequestDTO request);
    void delete(Long id);
    SeasonResponseDTO getById(Long id);
    List<SeasonResponseDTO> getAll();
}
