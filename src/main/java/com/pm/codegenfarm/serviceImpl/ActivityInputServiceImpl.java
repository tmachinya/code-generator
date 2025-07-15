package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.request.ActivityInputRequestDTO;
import com.pm.codegenfarm.dto.response.ActivityInputResponseDTO;
import com.pm.codegenfarm.entity.ActivityInput;
import com.pm.codegenfarm.mapper.ActivityInputMapper;
import com.pm.codegenfarm.repository.ActivityInputRepository;
import com.pm.codegenfarm.service.ActivityInputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ActivityInputServiceImpl implements ActivityInputService {

    private final ActivityInputRepository repository;
    private final ActivityInputMapper mapper;

    @Override
    public ActivityInputResponseDTO create(ActivityInputRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public ActivityInputResponseDTO update(Long id, ActivityInputRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ActivityInputResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<ActivityInputResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }
}
