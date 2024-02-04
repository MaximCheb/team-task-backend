package org.bear.comp.task.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bear.comp.task.api.model.UserProfile;
import org.bear.comp.task.api.model.dto.JwtAuthenticationResponseDto;
import org.bear.comp.task.api.model.dto.LoginRequestDto;
import org.bear.comp.task.api.model.dto.SignUpRequestDto;
import org.bear.comp.task.api.security.JwtService;
import org.bear.comp.task.api.utils.UuidUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;
    public JwtAuthenticationResponseDto signUp(SignUpRequestDto request) {

        var user = UserProfile.builder()
                .uuid(UuidUtils.generateV1())
                .createDate(new Date(System.currentTimeMillis()))
                .active(true)
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRoles(roleService.getStandardRoles())
                .build();

        userService.createUser(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponseDto(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponseDto signIn(LoginRequestDto request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));

            var jwt = jwtService.generateToken(userService.getByUsername(request.getUsername()));
            return new JwtAuthenticationResponseDto(jwt);
        } catch (RuntimeException runtimeException){
            log.warn("user not auth = {}", request.getUsername(), runtimeException);
            return new JwtAuthenticationResponseDto("empty token");
        }
    }
}
