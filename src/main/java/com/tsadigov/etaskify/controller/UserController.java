package com.tsadigov.etaskify.controller;

import com.tsadigov.etaskify.dto.ResponseDTO;
import com.tsadigov.etaskify.dto.SignUpDTO;
import com.tsadigov.etaskify.dto.UserCreationDTO;
import com.tsadigov.etaskify.domain.AppUser;
import com.tsadigov.etaskify.domain.Employee;
import com.tsadigov.etaskify.dto.UserDTO;
import com.tsadigov.etaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.tsadigov.etaskify.bootstap.Constants.*;
import static com.tsadigov.etaskify.bootstap.Constants.CREATED;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<ResponseDTO> createUser(@RequestBody UserCreationDTO userCreationDTO) {

        Employee employee = userService.createUser(userCreationDTO);

        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS)
                .response(employee)
                .build();

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/signup")
    ResponseEntity<ResponseDTO> signUp(@RequestBody SignUpDTO signUpDTO) {

        userService.signUp(signUpDTO);

        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(CREATED_CODE)
                .message(CREATED)
                .build();

        return ResponseEntity.ok()
                .body(responseDTO);
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseDTO> getUser(@PathVariable Long id) {

        UserDTO user = userService.getUser(id);

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
