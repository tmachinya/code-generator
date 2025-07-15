package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.request.SeasonRequestDTO;
import com.pm.codegenfarm.dto.response.SeasonResponseDTO;
import com.pm.codegenfarm.service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/season")
@RequiredArgsConstructor
public class SeasonController {

    private final SeasonService seasonService;

    @PostMapping
    public ResponseEntity<SeasonResponseDTO> create(@RequestBody SeasonRequestDTO request) {
        return ResponseEntity.ok(seasonService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeasonResponseDTO> update(@PathVariable Long id, @RequestBody SeasonRequestDTO request) {
        return ResponseEntity.ok(seasonService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        seasonService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeasonResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(seasonService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SeasonResponseDTO>> getAll() {
        return ResponseEntity.ok(seasonService.getAll());
    }
}
