package com.tsadigov.etaskify.controller;

import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    Optional<AppUser> getOne(@PathVariable Long id) {
        Optional<AppUser> user = userService.getUser(id);
        return user;
    }


}
