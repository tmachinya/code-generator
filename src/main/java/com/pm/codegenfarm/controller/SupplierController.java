package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.SupplierRequestDTO;
import com.pm.codegenfarm.dto.SupplierResponseDTO;
import com.pm.codegenfarm.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> create(@RequestBody SupplierRequestDTO request) {
        return ResponseEntity.ok(supplierService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> update(@PathVariable Long id, @RequestBody SupplierRequestDTO request) {
        return ResponseEntity.ok(supplierService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> getAll() {
        return ResponseEntity.ok(supplierService.getAll());
    }
}
