package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.ActivityRequestDTO;
import com.pm.codegenfarm.dto.ActivityResponseDTO;
import com.pm.codegenfarm.entity.Activity;
import com.pm.codegenfarm.mapper.ActivityMapper;
import com.pm.codegenfarm.repository.ActivityRepository;
import com.pm.codegenfarm.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository repository;
    private final ActivityMapper mapper;

    @Override
    public ActivityResponseDTO create(ActivityRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public ActivityResponseDTO update(Long id, ActivityRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ActivityResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toResponseDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<ActivityResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toResponseDto)
            .collect(Collectors.toList());
    }
}
