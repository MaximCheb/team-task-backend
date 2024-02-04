package org.bear.comp.task.api.security;

import org.bear.comp.task.api.service.ProjectService;
import org.bear.comp.task.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Autowired
    UserService userService;
    @Autowired
    ProjectService projectService;
    public boolean projectCanRead(long projectId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            auth.getName();
            return true;
        }
        return false;
    }

    public boolean projectCanWrite(long projectId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            auth.getName();
            return true;
        }
        return false;
    }
}
