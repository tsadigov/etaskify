package com.tsadigov.etaskify.controller;

import com.tsadigov.etaskify.dto.ResponseDTO;
import com.tsadigov.etaskify.dto.SignUpDTO;
import com.tsadigov.etaskify.domain.Organization;
import com.tsadigov.etaskify.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.tsadigov.etaskify.bootstap.Constants.*;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/{id}")
    ResponseEntity<ResponseDTO> getOne(@PathVariable Long id) {

        Organization organization = organizationService.getOne(id);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS)
                .response(organization)
                .build();

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    ResponseEntity<ResponseDTO> getAll() {

        List<Organization> organizations = organizationService.getAll();
        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS)
                .response(organizations)
                .build();

        return ResponseEntity.ok(responseDTO);
    }

}
