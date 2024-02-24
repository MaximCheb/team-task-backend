package org.bear.comp.task.api.controller;

import lombok.RequiredArgsConstructor;
import org.bear.comp.task.api.service.ProjectRoleService;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProjectRoleController {
    private final ProjectRoleService projectRoleService;
}
