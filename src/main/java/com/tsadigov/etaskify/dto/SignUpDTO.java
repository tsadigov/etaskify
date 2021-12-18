package com.tsadigov.etaskify.dto;

import com.tsadigov.etaskify.validator.Validator;
import lombok.Data;
import org.springframework.security.authentication.BadCredentialsException;

import static com.tsadigov.etaskify.bootstap.Constants.WRONG_EMAIL_FORMAT;
import static com.tsadigov.etaskify.bootstap.Constants.WRONG_PASSWORD_FORMAT;

@Data
public class SignUpDTO {

    private String organizationName;
    private String phoneNumber;
    private String address;
    private String username;
    private String email;
    private String password;

    public String getEmail() {
        if (Validator.isValidEmailFormat(email)) return email;
        else throw new BadCredentialsException(WRONG_EMAIL_FORMAT);
    }

    public String getPassword() {
        if (Validator.isValidPasswordFormat(password)) return password;
        else throw new BadCredentialsException(WRONG_PASSWORD_FORMAT);
    }
}
