package org.bear.comp.task.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bear.comp.task.api.model.SystemRole;
import org.bear.comp.task.api.service.SystemRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/system-role")
@Slf4j
@RestController
public class SystemRoleController {
    private final SystemRoleService systemRoleService;

    @GetMapping("/all")
    public ResponseEntity<List<SystemRole>> getAll(){
        return ResponseEntity.ok(systemRoleService.getAll());
    }
    @GetMapping("/{uuid}")
    public ResponseEntity<SystemRole> getRole(@PathVariable UUID uuid){
        return ResponseEntity.ok(systemRoleService.getByUuid(uuid));
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<SystemRole> getByCode(@PathVariable String code){
        return ResponseEntity.ok(systemRoleService.getByCode(code));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<SystemRole> getByName(@PathVariable String name){
        return ResponseEntity.ok(systemRoleService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody SystemRole systemRole){
        return ResponseEntity.ok(systemRoleService.upsert(systemRole).getUuid());
    }

    @PutMapping
    public ResponseEntity<UUID> update(@RequestBody SystemRole systemRole){
        return ResponseEntity.ok(systemRoleService.upsert(systemRole).getUuid());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<UUID> delete(@PathVariable UUID uuid){
        return ResponseEntity.ok(systemRoleService.delete(uuid));
    }
}
