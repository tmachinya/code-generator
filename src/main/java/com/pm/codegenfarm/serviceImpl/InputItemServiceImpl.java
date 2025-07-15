package com.pm.codegenfarm.serviceImpl;

import com.pm.codegenfarm.dto.InputItemRequestDTO;
import com.pm.codegenfarm.dto.InputItemResponseDTO;
import com.pm.codegenfarm.entity.InputItem;
import com.pm.codegenfarm.mapper.InputItemMapper;
import com.pm.codegenfarm.repository.InputItemRepository;
import com.pm.codegenfarm.service.InputItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InputItemServiceImpl implements InputItemService {

    private final InputItemRepository repository;
    private final InputItemMapper mapper;

    @Override
    public InputItemResponseDTO create(InputItemRequestDTO request) {
        var entity = mapper.toEntity(request);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public InputItemResponseDTO update(Long id, InputItemRequestDTO request) {
        var entity = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        mapper.updateEntity(entity, request);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public InputItemResponseDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toResponseDto)
            .orElseThrow(() -> new NoSuchElementException("Not found"));
    }

    @Override
    public List<InputItemResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toResponseDto)
            .collect(Collectors.toList());
    }
}
