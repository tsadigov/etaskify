package com.tsadigov.etaskify.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseDTO {
    private Integer code;
    private String message;
    private Object response;
}
