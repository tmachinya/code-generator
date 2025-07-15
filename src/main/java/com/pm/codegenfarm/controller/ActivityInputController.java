package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.ActivityInputRequestDTO;
import com.pm.codegenfarm.dto.ActivityInputResponseDTO;
import com.pm.codegenfarm.service.ActivityInputService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activity_input")
@RequiredArgsConstructor
public class ActivityInputController {

    private final ActivityInputService activityInputService;

    @PostMapping
    public ResponseEntity<ActivityInputResponseDTO> create(@RequestBody ActivityInputRequestDTO request) {
        return ResponseEntity.ok(activityInputService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityInputResponseDTO> update(@PathVariable Long id, @RequestBody ActivityInputRequestDTO request) {
        return ResponseEntity.ok(activityInputService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityInputService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityInputResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(activityInputService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ActivityInputResponseDTO>> getAll() {
        return ResponseEntity.ok(activityInputService.getAll());
    }
}
