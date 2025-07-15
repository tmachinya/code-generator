package com.pm.codegenfarm.service;

import com.pm.codegenfarm.dto.ActivityRequestDTO;
import com.pm.codegenfarm.dto.ActivityResponseDTO;
import java.util.List;

public interface ActivityService {

    ActivityResponseDTO create(ActivityRequestDTO request);
    ActivityResponseDTO update(Long id, ActivityRequestDTO request);
    void delete(Long id);
    ActivityResponseDTO getById(Long id);
    List<ActivityResponseDTO> getAll();
}
