package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.LaborAssignmentRequestDTO;
import com.pm.codegenfarm.dto.LaborAssignmentResponseDTO;
import com.pm.codegenfarm.service.LaborAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/labor_assignment")
@RequiredArgsConstructor
public class LaborAssignmentController {

    private final LaborAssignmentService laborAssignmentService;

    @PostMapping
    public ResponseEntity<LaborAssignmentResponseDTO> create(@RequestBody LaborAssignmentRequestDTO request) {
        return ResponseEntity.ok(laborAssignmentService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaborAssignmentResponseDTO> update(@PathVariable Long id, @RequestBody LaborAssignmentRequestDTO request) {
        return ResponseEntity.ok(laborAssignmentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        laborAssignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaborAssignmentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(laborAssignmentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<LaborAssignmentResponseDTO>> getAll() {
        return ResponseEntity.ok(laborAssignmentService.getAll());
    }
}
