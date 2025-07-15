package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.request.LaborRequestDTO;
import com.pm.codegenfarm.dto.response.LaborResponseDTO;
import com.pm.codegenfarm.entity.Labor;
import com.pm.codegenfarm.mapper.LaborMapper;
import com.pm.codegenfarm.repository.LaborRepository;
import com.pm.codegenfarm.service.LaborService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LaborServiceImpl implements LaborService {

    private final LaborRepository repository;
    private final LaborMapper mapper;

    @Override
    public LaborResponseDTO create(LaborRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public LaborResponseDTO update(Long id, LaborRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public LaborResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<LaborResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }
}
