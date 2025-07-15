package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.FieldCropRequestDTO;
import com.pm.codegenfarm.dto.FieldCropResponseDTO;
import com.pm.codegenfarm.service.FieldCropService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/field_crop")
@RequiredArgsConstructor
public class FieldCropController {

    private final FieldCropService fieldCropService;

    @PostMapping
    public ResponseEntity<FieldCropResponseDTO> create(@RequestBody FieldCropRequestDTO request) {
        return ResponseEntity.ok(fieldCropService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldCropResponseDTO> update(@PathVariable Long id, @RequestBody FieldCropRequestDTO request) {
        return ResponseEntity.ok(fieldCropService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fieldCropService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldCropResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(fieldCropService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<FieldCropResponseDTO>> getAll() {
        return ResponseEntity.ok(fieldCropService.getAll());
    }
}
