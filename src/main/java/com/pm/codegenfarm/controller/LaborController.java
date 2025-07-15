package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.request.LaborRequestDTO;
import com.pm.codegenfarm.dto.response.LaborResponseDTO;
import com.pm.codegenfarm.service.LaborService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/labor")
@RequiredArgsConstructor
public class LaborController {

    private final LaborService laborService;

    @PostMapping
    public ResponseEntity<LaborResponseDTO> create(@RequestBody LaborRequestDTO request) {
        return ResponseEntity.ok(laborService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaborResponseDTO> update(@PathVariable Long id, @RequestBody LaborRequestDTO request) {
        return ResponseEntity.ok(laborService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        laborService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaborResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(laborService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<LaborResponseDTO>> getAll() {
        return ResponseEntity.ok(laborService.getAll());
    }
}
