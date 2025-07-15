package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.CropRevenueReferenceRequestDTO;
import com.pm.codegenfarm.dto.CropRevenueReferenceResponseDTO;
import com.pm.codegenfarm.entity.CropRevenueReference;
import com.pm.codegenfarm.mapper.CropRevenueReferenceMapper;
import com.pm.codegenfarm.repository.CropRevenueReferenceRepository;
import com.pm.codegenfarm.service.CropRevenueReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CropRevenueReferenceServiceImpl implements CropRevenueReferenceService {

    private final CropRevenueReferenceRepository repository;
    private final CropRevenueReferenceMapper mapper;

    @Override
    public CropRevenueReferenceResponseDTO create(CropRevenueReferenceRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public CropRevenueReferenceResponseDTO update(Long id, CropRevenueReferenceRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CropRevenueReferenceResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toResponseDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<CropRevenueReferenceResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toResponseDto)
            .collect(Collectors.toList());
    }
}
