package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.request.HarvestRequestDTO;
import com.pm.codegenfarm.dto.response.HarvestResponseDTO;
import com.pm.codegenfarm.entity.Harvest;
import com.pm.codegenfarm.mapper.HarvestMapper;
import com.pm.codegenfarm.repository.HarvestRepository;
import com.pm.codegenfarm.service.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository repository;
    private final HarvestMapper mapper;

    @Override
    public HarvestResponseDTO create(HarvestRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public HarvestResponseDTO update(Long id, HarvestRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public HarvestResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<HarvestResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }
}
