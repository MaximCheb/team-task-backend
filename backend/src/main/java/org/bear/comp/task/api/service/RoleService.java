package org.bear.comp.task.api.service;

import lombok.RequiredArgsConstructor;
import org.bear.comp.task.api.model.SystemRole;
import org.bear.comp.task.api.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public SystemRole getRoleByCode(String roleCode){
        return roleRepository.findByCode(roleCode);
    }

    public Set<SystemRole> getStandardRoles(){
        return Set.of(
            getRoleByCode("user")
        );
    }
}
