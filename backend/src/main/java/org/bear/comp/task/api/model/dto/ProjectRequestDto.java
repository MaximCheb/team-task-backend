package org.bear.comp.task.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectRequestDto {
    private String name;
    private String initials;
    private String description;
    private List<UserRoleDto> userRoles; // user uuid : project role uuid
}
