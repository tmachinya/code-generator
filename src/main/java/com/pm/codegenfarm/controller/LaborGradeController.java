package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.request.LaborGradeRequestDTO;
import com.pm.codegenfarm.dto.response.LaborGradeResponseDTO;
import com.pm.codegenfarm.service.LaborGradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/labor_grade")
@RequiredArgsConstructor
public class LaborGradeController {

    private final LaborGradeService laborGradeService;

    @PostMapping
    public ResponseEntity<LaborGradeResponseDTO> create(@RequestBody LaborGradeRequestDTO request) {
        return ResponseEntity.ok(laborGradeService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaborGradeResponseDTO> update(@PathVariable Long id, @RequestBody LaborGradeRequestDTO request) {
        return ResponseEntity.ok(laborGradeService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        laborGradeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaborGradeResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(laborGradeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<LaborGradeResponseDTO>> getAll() {
        return ResponseEntity.ok(laborGradeService.getAll());
    }
}
