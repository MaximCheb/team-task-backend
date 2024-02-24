package org.bear.comp.task.api.controller;

import lombok.RequiredArgsConstructor;
import org.bear.comp.task.api.model.Project;
import org.bear.comp.task.api.model.dto.ProjectRequestDto;
import org.bear.comp.task.api.service.ProjectService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping()
    public Project createProject(ProjectRequestDto project){
        return null;
    }
}
