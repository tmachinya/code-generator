package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.LaborGradeRequestDTO;
import com.pm.codegenfarm.dto.LaborGradeResponseDTO;
import com.pm.codegenfarm.entity.LaborGrade;
import com.pm.codegenfarm.mapper.LaborGradeMapper;
import com.pm.codegenfarm.repository.LaborGradeRepository;
import com.pm.codegenfarm.service.LaborGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LaborGradeServiceImpl implements LaborGradeService {

    private final LaborGradeRepository repository;
    private final LaborGradeMapper mapper;

    @Override
    public LaborGradeResponseDTO create(LaborGradeRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public LaborGradeResponseDTO update(Long id, LaborGradeRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public LaborGradeResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toResponseDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<LaborGradeResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toResponseDto)
            .collect(Collectors.toList());
    }
}
