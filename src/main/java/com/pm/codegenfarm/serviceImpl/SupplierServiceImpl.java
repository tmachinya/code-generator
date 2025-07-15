package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.request.SupplierRequestDTO;
import com.pm.codegenfarm.dto.response.SupplierResponseDTO;
import com.pm.codegenfarm.entity.Supplier;
import com.pm.codegenfarm.mapper.SupplierMapper;
import com.pm.codegenfarm.repository.SupplierRepository;
import com.pm.codegenfarm.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository repository;
    private final SupplierMapper mapper;

    @Override
    public SupplierResponseDTO create(SupplierRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public SupplierResponseDTO update(Long id, SupplierRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public SupplierResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<SupplierResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }
}
