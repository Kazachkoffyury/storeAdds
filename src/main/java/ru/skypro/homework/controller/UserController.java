package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<String>  getUsers() {
        return  new ResponseEntity<>("получены все пользователи", HttpStatus.OK);
    }

    @PutMapping("/me")
    public ResponseEntity<String> updateUser() {
        return  new ResponseEntity<>("обновлен пользователь", HttpStatus.OK);
    }

    @PostMapping("/set_password")
    public ResponseEntity<String> setPassword() {
        return  new ResponseEntity<>("установлен пароль",HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<String>  getUser(@PathVariable int id) {
        return  new ResponseEntity<>("получен пользователь", HttpStatus.OK);
    }



}
