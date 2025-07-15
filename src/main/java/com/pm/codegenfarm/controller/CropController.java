package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.request.CropRequestDTO;
import com.pm.codegenfarm.dto.response.CropResponseDTO;
import com.pm.codegenfarm.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/crop")
@RequiredArgsConstructor
public class CropController {

    private final CropService cropService;

    @PostMapping
    public ResponseEntity<CropResponseDTO> create(@RequestBody CropRequestDTO request) {
        return ResponseEntity.ok(cropService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CropResponseDTO> update(@PathVariable Long id, @RequestBody CropRequestDTO request) {
        return ResponseEntity.ok(cropService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cropService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cropService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CropResponseDTO>> getAll() {
        return ResponseEntity.ok(cropService.getAll());
    }
}
