package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.CropInputReferenceRequestDTO;
import com.pm.codegenfarm.dto.CropInputReferenceResponseDTO;
import com.pm.codegenfarm.entity.CropInputReference;
import com.pm.codegenfarm.mapper.CropInputReferenceMapper;
import com.pm.codegenfarm.repository.CropInputReferenceRepository;
import com.pm.codegenfarm.service.CropInputReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CropInputReferenceServiceImpl implements CropInputReferenceService {

    private final CropInputReferenceRepository repository;
    private final CropInputReferenceMapper mapper;

    @Override
    public CropInputReferenceResponseDTO create(CropInputReferenceRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public CropInputReferenceResponseDTO update(Long id, CropInputReferenceRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CropInputReferenceResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toResponseDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<CropInputReferenceResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toResponseDto)
            .collect(Collectors.toList());
    }
}
