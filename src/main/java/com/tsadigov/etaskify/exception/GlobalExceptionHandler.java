package com.tsadigov.etaskify.exception;

import com.tsadigov.etaskify.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static com.tsadigov.etaskify.bootstap.Constants.*;

@RestControllerAdvice(basePackages = {})
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseDTO> notFoundExceptionHandling(ResourceNotFoundException exception, WebRequest request) {

        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(NOT_FOUND_CODE)
                .message(exception.getLocalizedMessage())
                .response(null).build();

        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseDTO> badCredentials(BadCredentialsException exception, WebRequest request) {

        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(UNAUTHORIZED_CODE)
                .message(exception.getMessage())
                .response(null).build();

        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> phoneNumberValidationException(MethodArgumentNotValidException exception, WebRequest request) {

        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(BAD_REQUEST_CODE)
                .message(exception.getFieldError().getDefaultMessage())
                .response(null)
                .build();

        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ResponseDTO> alreadyExist(AlreadyExistException exception){
        ResponseDTO responseDTO = ResponseDTO.builder()
                .code(FORBIDDEN_CODE)
                .message(exception.getLocalizedMessage())
                .response(null)
                .build();

        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }
}
