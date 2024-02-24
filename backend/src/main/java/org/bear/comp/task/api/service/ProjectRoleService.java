package org.bear.comp.task.api.service;

import lombok.RequiredArgsConstructor;
import org.bear.comp.task.api.model.ProjectRole;
import org.bear.comp.task.api.repository.ProjectRoleRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectRoleService {
    private final ProjectRoleRepository projectRoleRepository;

    public ProjectRole getMinimal(){
        return null;
    }
}
