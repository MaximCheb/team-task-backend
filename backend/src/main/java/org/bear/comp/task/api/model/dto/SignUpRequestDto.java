package org.bear.comp.task.api.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@Schema(description = "Запрос на регистрацию")
public class SignUpRequestDto {

    @Size(min = 4, max = 64)
    @NotBlank(message = "Username is not blank")
    private String username;

    @Schema(description = "Адрес электронной почты")
    @Size(min = 5, max = 128, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank(message = "Not blank field")
    @Email(message = "Email format user@example.com")
    private String email;

    @Schema(description = "Адрес электронной почты")
    @Size(min = 5, max = 128, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank(message = "Not blank field")
    private String phone;

    @Schema(description = "Пароль")
    @Size(min = 8, max = 255, message = "Длина пароля должна быть не более 255 символов")
    private String password;
}
