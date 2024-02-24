package org.bear.comp.task.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bear.comp.task.api.model.UserProfile;
import org.bear.comp.task.api.model.dto.LoginRequestDto;
import org.bear.comp.task.api.model.dto.SignUpRequestDto;
import org.bear.comp.task.api.service.AuthService;
import org.bear.comp.task.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity login(@RequestBody LoginRequestDto loginDto){
        return ResponseEntity.ok(authService.signIn(loginDto));
    }

    @PostMapping("/sign-out")
    public ResponseEntity registration(@RequestBody SignUpRequestDto profile){
        log.info("create user: {}", profile.getUsername());
        return ResponseEntity.ok(authService.signUp(profile));
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.ok("logout");
    }
}
