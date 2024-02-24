package org.bear.comp.task.api.service;

import lombok.RequiredArgsConstructor;
import org.bear.comp.task.api.model.Project;
import org.bear.comp.task.api.model.ProjectInvolve;
import org.bear.comp.task.api.model.dto.UserRoleDto;
import org.bear.comp.task.api.repository.ProjectInvolveRepository;
import org.bear.comp.task.api.repository.ProjectRoleRepository;
import org.bear.comp.task.api.repository.UserRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProjectInvolveService {
    private final ProjectInvolveRepository involveRepository;
    private final ProjectRoleRepository roleRepository;
    private final UserRepository userRepository;

    public void addProjectTeam(List<UserRoleDto> users, Project project){
        involveRepository.saveAll(getUserProjectRoles(users, project));
    }

    public ProjectInvolve getByUuid(UUID uuid){
        return involveRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }

    private List<ProjectInvolve> getUserProjectRoles(List<UserRoleDto> users, Project project){
        ArrayList<ProjectInvolve> projectTeam = new ArrayList<>();
        for(UserRoleDto dto: users){
            var user = userRepository.findByUuid(UUID.fromString(dto.user()));
            var role = roleRepository.findByUuid(UUID.fromString(dto.role()));
            if(user.isPresent() && role.isPresent()){
                ProjectInvolve projectInvolve = ProjectInvolve.builder()
                        .project(project)
                        .user(user.get())
                        .role(role.get())
                        .build();
                projectTeam.add(projectInvolve);
            }
        }
        return projectTeam;
    }
}
