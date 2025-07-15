package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.CropInputReferenceRequestDTO;
import com.pm.codegenfarm.dto.CropInputReferenceResponseDTO;
import com.pm.codegenfarm.service.CropInputReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/crop_input_reference")
@RequiredArgsConstructor
public class CropInputReferenceController {

    private final CropInputReferenceService cropInputReferenceService;

    @PostMapping
    public ResponseEntity<CropInputReferenceResponseDTO> create(@RequestBody CropInputReferenceRequestDTO request) {
        return ResponseEntity.ok(cropInputReferenceService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CropInputReferenceResponseDTO> update(@PathVariable Long id, @RequestBody CropInputReferenceRequestDTO request) {
        return ResponseEntity.ok(cropInputReferenceService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cropInputReferenceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropInputReferenceResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cropInputReferenceService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CropInputReferenceResponseDTO>> getAll() {
        return ResponseEntity.ok(cropInputReferenceService.getAll());
    }
}
