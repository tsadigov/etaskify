package com.tsadigov.etaskify.controller;

import com.tsadigov.etaskify.Dto.ResponseDTO;
import com.tsadigov.etaskify.Dto.SignUpDTO;
import com.tsadigov.etaskify.domain.Organization;
import com.tsadigov.etaskify.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.tsadigov.etaskify.config.Constants.SUCCESS;
import static com.tsadigov.etaskify.config.Constants.SUCCESS_CODE;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping
    ResponseEntity<ResponseDTO> getAll(){
        List<Organization> organizations = organizationService.getAll();
        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS)
                .response(organizations)
                .build();

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    Optional<Organization> getOne(@PathVariable Long id){
        Optional<Organization> organization = organizationService.getOne(id);
        return organization;
    }

    @PostMapping
    ResponseEntity<ResponseDTO>createOrganization(@RequestBody SignUpDTO signUpDTO){

//        System.out.println("SIGNUP DTO -------- "+signUpDTO);
        organizationService.signUp(signUpDTO);

        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(200)
                .message("Organization created")
                .response(SUCCESS)
                .build();

        return ResponseEntity.ok()
                .body(responseDTO);
    }

}
