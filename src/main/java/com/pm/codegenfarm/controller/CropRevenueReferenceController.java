package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.request.CropRevenueReferenceRequestDTO;
import com.pm.codegenfarm.dto.response.CropRevenueReferenceResponseDTO;
import com.pm.codegenfarm.service.CropRevenueReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/crop_revenue_reference")
@RequiredArgsConstructor
public class CropRevenueReferenceController {

    private final CropRevenueReferenceService cropRevenueReferenceService;

    @PostMapping
    public ResponseEntity<CropRevenueReferenceResponseDTO> create(@RequestBody CropRevenueReferenceRequestDTO request) {
        return ResponseEntity.ok(cropRevenueReferenceService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CropRevenueReferenceResponseDTO> update(@PathVariable Long id, @RequestBody CropRevenueReferenceRequestDTO request) {
        return ResponseEntity.ok(cropRevenueReferenceService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cropRevenueReferenceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropRevenueReferenceResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cropRevenueReferenceService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CropRevenueReferenceResponseDTO>> getAll() {
        return ResponseEntity.ok(cropRevenueReferenceService.getAll());
    }
}
