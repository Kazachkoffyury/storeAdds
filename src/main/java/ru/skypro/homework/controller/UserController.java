package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/me")
    public ResponseEntity<User>  getUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUsername(authentication.getName());
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("/me")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(userService.updateUser(userDto, authentication));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@Valid @RequestBody NewPasswordDto newPasswordDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authService.changePassword(
                authentication.getName(),
                newPasswordDto.getCurrentPassword(),
                newPasswordDto.getNewPassword()
        );
        return ResponseEntity.ok(newPasswordDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getAnyUser(@Positive @PathVariable("id") int id) {
        return ResponseEntity.ok(userService.getUser(id));
    }


}