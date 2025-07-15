package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.SaleRequestDTO;
import com.pm.codegenfarm.dto.SaleResponseDTO;
import com.pm.codegenfarm.entity.Sale;
import com.pm.codegenfarm.mapper.SaleMapper;
import com.pm.codegenfarm.repository.SaleRepository;
import com.pm.codegenfarm.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository repository;
    private final SaleMapper mapper;

    @Override
    public SaleResponseDTO create(SaleRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public SaleResponseDTO update(Long id, SaleRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public SaleResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toResponseDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<SaleResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toResponseDto)
            .collect(Collectors.toList());
    }
}
