package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.request.LaborGradeRequestDTO;
import com.pm.codegenfarm.dto.response.LaborGradeResponseDTO;
import java.util.List;

public interface LaborGradeService {

    LaborGradeResponseDTO create(LaborGradeRequestDTO request);
    LaborGradeResponseDTO update(Long id, LaborGradeRequestDTO request);
    void delete(Long id);
    LaborGradeResponseDTO getById(Long id);
    List<LaborGradeResponseDTO> getAll();
}
