package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.InputItemRequestDTO;
import com.pm.codegenfarm.dto.InputItemResponseDTO;
import com.pm.codegenfarm.service.InputItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/input_item")
@RequiredArgsConstructor
public class InputItemController {

    private final InputItemService inputItemService;

    @PostMapping
    public ResponseEntity<InputItemResponseDTO> create(@RequestBody InputItemRequestDTO request) {
        return ResponseEntity.ok(inputItemService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InputItemResponseDTO> update(@PathVariable Long id, @RequestBody InputItemRequestDTO request) {
        return ResponseEntity.ok(inputItemService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inputItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InputItemResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(inputItemService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<InputItemResponseDTO>> getAll() {
        return ResponseEntity.ok(inputItemService.getAll());
    }
}
