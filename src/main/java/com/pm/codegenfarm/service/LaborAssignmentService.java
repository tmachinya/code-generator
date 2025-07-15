package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.request.LaborAssignmentRequestDTO;
import com.pm.codegenfarm.dto.response.LaborAssignmentResponseDTO;
import java.util.List;

public interface LaborAssignmentService {

    LaborAssignmentResponseDTO create(LaborAssignmentRequestDTO request);
    LaborAssignmentResponseDTO update(Long id, LaborAssignmentRequestDTO request);
    void delete(Long id);
    LaborAssignmentResponseDTO getById(Long id);
    List<LaborAssignmentResponseDTO> getAll();
}
