package com.pm.codegenfarm.controller;

import com.pm.codegenfarm.dto.request.ActivityRequestDTO;
import com.pm.codegenfarm.dto.response.ActivityResponseDTO;
import com.pm.codegenfarm.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponseDTO> create(@RequestBody ActivityRequestDTO request) {
        return ResponseEntity.ok(activityService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponseDTO> update(@PathVariable Long id, @RequestBody ActivityRequestDTO request) {
        return ResponseEntity.ok(activityService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(activityService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponseDTO>> getAll() {
        return ResponseEntity.ok(activityService.getAll());
    }
}
