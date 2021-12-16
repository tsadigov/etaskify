package com.tsadigov.etaskify.Dto;

import lombok.Data;

@Data
public class SignUpDTO {

    private String organizationName;
    private String phoneNumber;
    private String address;
    private String username;
    private String email;
    private String password;

}
