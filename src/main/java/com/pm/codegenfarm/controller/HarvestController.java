package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.request.HarvestRequestDTO;
import com.pm.codegenfarm.dto.response.HarvestResponseDTO;
import com.pm.codegenfarm.service.HarvestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/harvest")
@RequiredArgsConstructor
public class HarvestController {

    private final HarvestService harvestService;

    @PostMapping
    public ResponseEntity<HarvestResponseDTO> create(@RequestBody HarvestRequestDTO request) {
        return ResponseEntity.ok(harvestService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HarvestResponseDTO> update(@PathVariable Long id, @RequestBody HarvestRequestDTO request) {
        return ResponseEntity.ok(harvestService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        harvestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(harvestService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<HarvestResponseDTO>> getAll() {
        return ResponseEntity.ok(harvestService.getAll());
    }
}
