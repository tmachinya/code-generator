package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.request.CropRequestDTO;
import com.pm.codegenfarm.dto.response.CropResponseDTO;
import com.pm.codegenfarm.entity.Crop;
import com.pm.codegenfarm.mapper.CropMapper;
import com.pm.codegenfarm.repository.CropRepository;
import com.pm.codegenfarm.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    private final CropRepository repository;
    private final CropMapper mapper;

    @Override
    public CropResponseDTO create(CropRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public CropResponseDTO update(Long id, CropRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CropResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<CropResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }
}
