package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.request.ActivityInputRequestDTO;
import com.pm.codegenfarm.dto.response.ActivityInputResponseDTO;
import java.util.List;

public interface ActivityInputService {

    ActivityInputResponseDTO create(ActivityInputRequestDTO request);
    ActivityInputResponseDTO update(Long id, ActivityInputRequestDTO request);
    void delete(Long id);
    ActivityInputResponseDTO getById(Long id);
    List<ActivityInputResponseDTO> getAll();
}
