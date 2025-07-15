package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.CostCenterRequestDTO;
import com.pm.codegenfarm.dto.CostCenterResponseDTO;
import com.pm.codegenfarm.service.CostCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cost_center")
@RequiredArgsConstructor
public class CostCenterController {

    private final CostCenterService costCenterService;

    @PostMapping
    public ResponseEntity<CostCenterResponseDTO> create(@RequestBody CostCenterRequestDTO request) {
        return ResponseEntity.ok(costCenterService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostCenterResponseDTO> update(@PathVariable Long id, @RequestBody CostCenterRequestDTO request) {
        return ResponseEntity.ok(costCenterService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        costCenterService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostCenterResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(costCenterService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CostCenterResponseDTO>> getAll() {
        return ResponseEntity.ok(costCenterService.getAll());
    }
}
