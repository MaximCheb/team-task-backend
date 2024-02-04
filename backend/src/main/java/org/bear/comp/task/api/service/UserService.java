package org.bear.comp.task.api.service;

import lombok.RequiredArgsConstructor;
import org.bear.comp.task.api.model.CustomUserDetails;
import org.bear.comp.task.api.model.UserProfile;
import org.bear.comp.task.api.model.dto.LoginRequestDto;
import org.bear.comp.task.api.repository.UserRepository;
import org.bear.comp.task.api.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserProfile createUser(UserProfile profile){
        var dto = userRepository.save(profile);
        return dto;
    }

    public UserProfile login(LoginRequestDto requestDto){
        var optUserProfile = getUser(requestDto);
        if(optUserProfile.isPresent()){
            return optUserProfile.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    public List<UserProfile> getUsersByProject(){
        return List.of();
    }

    public List<UserProfile> getUsersSpace(){
        return List.of();
    }

    private Optional<UserProfile> getUser(LoginRequestDto loginDto){

        var userProfile = userRepository.findByUsername(loginDto.getUsername());
        if(userProfile.isPresent()){
            return userProfile;
        }
        userProfile = userRepository.findByEmail(loginDto.getUsername());
        if(userProfile.isPresent()){
            return userProfile;
        }
        return Optional.empty();
    }

    public UserProfile getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public CustomUserDetails getByLogin(String username) {
        return new CustomUserDetails(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден")));

    }

    public UserDetailsService userDetailsService() {
        return this::getByLogin;
    }
}
