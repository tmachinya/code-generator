package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.LaborAssignmentRequestDTO;
import com.pm.codegenfarm.dto.LaborAssignmentResponseDTO;
import java.util.List;

public interface LaborAssignmentService {

    LaborAssignmentResponseDTO create(LaborAssignmentRequestDTO request);
    LaborAssignmentResponseDTO update(Long id, LaborAssignmentRequestDTO request);
    void delete(Long id);
    LaborAssignmentResponseDTO getById(Long id);
    List<LaborAssignmentResponseDTO> getAll();
}
