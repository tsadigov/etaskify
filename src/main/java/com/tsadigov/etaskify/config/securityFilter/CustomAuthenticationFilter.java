package com.tsadigov.etaskify.config.securityFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsadigov.etaskify.Dto.LoginDTO;
import com.tsadigov.etaskify.Dto.ResponseDTO;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

import static com.tsadigov.etaskify.config.Constants.*;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        String username = "";
        String password = "";
        String requestData = request.getReader().lines().collect(Collectors.joining());
        System.out.println("REQUEST DATA: "+requestData);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            LoginDTO loginDTO = objectMapper.readValue(requestData, LoginDTO.class);
            username = loginDTO.getUsername();
            password = loginDTO.getPassword();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        User user = (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());

        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_DURATION))
                .withIssuer(request.getRequestURL().toString())
                .withClaim(ROLES,user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_DURATION))
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        response.setHeader(ACCESS_TOKEN,access_token);
        response.setHeader(REFRESH_TOKEN,refresh_token);
        response.setHeader("Access-Control-Allow-Origin","*");

        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(SUCCESS_CODE)
                .message(SUCCESS)
                .build();

        new ObjectMapper().writeValue(response.getOutputStream(), ResponseEntity.ok().body(responseDTO));

    }
}
