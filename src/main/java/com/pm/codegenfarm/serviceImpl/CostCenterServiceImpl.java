package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.request.CostCenterRequestDTO;
import com.pm.codegenfarm.dto.response.CostCenterResponseDTO;
import com.pm.codegenfarm.entity.CostCenter;
import com.pm.codegenfarm.mapper.CostCenterMapper;
import com.pm.codegenfarm.repository.CostCenterRepository;
import com.pm.codegenfarm.service.CostCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CostCenterServiceImpl implements CostCenterService {

    private final CostCenterRepository repository;
    private final CostCenterMapper mapper;

    @Override
    public CostCenterResponseDTO create(CostCenterRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public CostCenterResponseDTO update(Long id, CostCenterRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CostCenterResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<CostCenterResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }
}
