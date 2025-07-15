package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.request.FieldCropRequestDTO;
import com.pm.codegenfarm.dto.response.FieldCropResponseDTO;
import com.pm.codegenfarm.entity.FieldCrop;
import com.pm.codegenfarm.mapper.FieldCropMapper;
import com.pm.codegenfarm.repository.FieldCropRepository;
import com.pm.codegenfarm.service.FieldCropService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class FieldCropServiceImpl implements FieldCropService {

    private final FieldCropRepository repository;
    private final FieldCropMapper mapper;

    @Override
    public FieldCropResponseDTO create(FieldCropRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public FieldCropResponseDTO update(Long id, FieldCropRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public FieldCropResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<FieldCropResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }
}
