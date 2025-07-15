package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.request.LaborAssignmentRequestDTO;
import com.pm.codegenfarm.dto.response.LaborAssignmentResponseDTO;
import com.pm.codegenfarm.entity.LaborAssignment;
import com.pm.codegenfarm.mapper.LaborAssignmentMapper;
import com.pm.codegenfarm.repository.LaborAssignmentRepository;
import com.pm.codegenfarm.service.LaborAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LaborAssignmentServiceImpl implements LaborAssignmentService {

    private final LaborAssignmentRepository repository;
    private final LaborAssignmentMapper mapper;

    @Override
    public LaborAssignmentResponseDTO create(LaborAssignmentRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public LaborAssignmentResponseDTO update(Long id, LaborAssignmentRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public LaborAssignmentResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<LaborAssignmentResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }
}
