package com.tsadigov.etaskify.controller;

import com.tsadigov.etaskify.dto.ResponseDTO;
import com.tsadigov.etaskify.dto.UserDTO;
import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.domain.Employee;
import com.tsadigov.etaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.tsadigov.etaskify.bootstap.Constants.SUCCESS;
import static com.tsadigov.etaskify.bootstap.Constants.SUCCESS_CODE;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO userDTO) {

        Employee employee = userService.createUser(userDTO);

        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS)
                .response(employee)
                .build();

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseDTO> getUser(@PathVariable Long id) {

        Optional<AppUser> user = userService.getUser(id);

        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS)
                .response(user)
                .build();

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    ResponseEntity<ResponseDTO> getUsers() {

        List<AppUser> users = userService.getUsers();

        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS)
                .response(users)
                .build();

        return ResponseEntity.ok(responseDTO);
    }

}
