package org.bear.comp.task.api.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.Lists;
import org.bear.comp.task.api.model.SystemRole;
import org.bear.comp.task.api.repository.SystemRoleRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SystemRoleService {
    private final SystemRoleRepository systemRoleRepository;

    public SystemRole get(Long id){
        return systemRoleRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public SystemRole getByUuid(UUID uuid){
        return systemRoleRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public SystemRole getByCode(String code){
        return systemRoleRepository.findByCode(code).orElseThrow(
                () -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public SystemRole getByName(String name){
        return systemRoleRepository.findByName(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public List<SystemRole> getAll(){
        return Lists.newArrayList(systemRoleRepository.findAll().iterator());
    }

    @PreAuthorize("hasAuthority(‘ROLE_ADMIN’)")
    public SystemRole upsert(SystemRole systemRole){
        return systemRoleRepository.save(systemRole);
    }

    @PreAuthorize("hasAuthority(‘ROLE_ADMIN’)")
    public UUID delete(UUID uuid){
        var item = getByUuid(uuid);
        systemRoleRepository.deleteById(item.getId());
        return item.getUuid();
    }
}
