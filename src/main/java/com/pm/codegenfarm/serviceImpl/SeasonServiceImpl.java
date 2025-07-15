package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.request.SeasonRequestDTO;
import com.pm.codegenfarm.dto.response.SeasonResponseDTO;
import com.pm.codegenfarm.entity.Season;
import com.pm.codegenfarm.mapper.SeasonMapper;
import com.pm.codegenfarm.repository.SeasonRepository;
import com.pm.codegenfarm.service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SeasonServiceImpl implements SeasonService {

    private final SeasonRepository repository;
    private final SeasonMapper mapper;

    @Override
    public SeasonResponseDTO create(SeasonRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public SeasonResponseDTO update(Long id, SeasonRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public SeasonResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<SeasonResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
    }
}
