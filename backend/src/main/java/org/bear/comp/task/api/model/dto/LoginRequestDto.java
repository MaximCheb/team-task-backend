package org.bear.comp.task.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "Запрос на вход")
public class LoginRequestDto {
    @Schema(description = "email or login")
    @NotBlank(message = "Not blank field")
    private String username;
    @Schema(description = "Password")
    @NotBlank(message = "Password not blank field")
    private String password;
}
