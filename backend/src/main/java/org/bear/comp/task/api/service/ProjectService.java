package org.bear.comp.task.api.service;

import lombok.RequiredArgsConstructor;
import org.bear.comp.task.api.model.Project;
import org.bear.comp.task.api.model.ProjectInvolve;
import org.bear.comp.task.api.model.dto.ProjectRequestDto;
import org.bear.comp.task.api.model.dto.UserRoleDto;
import org.bear.comp.task.api.repository.ProjectInvolveRepository;
import org.bear.comp.task.api.repository.ProjectRepository;
import org.bear.comp.task.api.repository.ProjectRoleRepository;
import org.bear.comp.task.api.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectInvolveService projectInvolveService;
    @PreAuthorize("hasAuthority(‘ROLE_ADMIN’)")
    public  Page<Project> getAllProjects(Pageable pageable){
       return projectRepository.findAll(pageable);
    }

    public Project getById(Long id){
        return projectRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public Project getByUuid(UUID uuid){
        return projectRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public Project getByName(String name){
        return projectRepository.findByName(name).orElseThrow(
                () -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    public Project createSimple(Project project){
        return projectRepository.save(project);
    }

    public Project create(ProjectRequestDto project){
        project.getUserRoles();
        var resProject = Project.builder()
                .name(project.getName())
                .initials(project.getInitials())
                .description(project.getDescription())
                .build();
        return projectRepository.save(resProject);
    }
}
